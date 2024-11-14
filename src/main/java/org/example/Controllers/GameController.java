package org.example.Controllers;

import org.example.Game;
import org.example.Model.User;
import org.example.Services.GameService;
import org.example.Services.TextService;
import org.example.Services.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileNotFoundException;

@Controller
public class GameController {

    private final GameService gameService;
    private final TextService textService;
    private final UserService userService;

    // Конструктор для внедрения сервисов
    public GameController(GameService gameService, TextService textService, UserService userService) {
        this.gameService = gameService;
        this.textService = textService;
        this.userService = userService;
    }

    // Главная страница меню, проверка аутентификации и извлечение текста из базы
    @GetMapping("/")
    public String showMenu(Model model) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (currentUser == null) {
            return "redirect:/login"; // Если пользователь не авторизован, перенаправляем на страницу логина
        }

        String content = textService.getText(1); // Получаем текст с ID = 1 из базы данных
        if(content == null) {
            model.addAttribute("error", "Текст не найден в базе данных!");
        } else {
            model.addAttribute("content", content); // Передаем текст в модель
        }

        model.addAttribute("games", gameService.getAllGames()); // Передаем игры в модель
        return "menu"; // Отображаем меню игры
    }

    // Метод для игры
    @PostMapping("/play")
    public String playGame(@RequestParam("gameId") int gameId, Model model) throws FileNotFoundException {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (currentUser == null) {
            return "redirect:/login"; // Если пользователь не авторизован, перенаправляем на страницу логина
        }

        Game game = gameService.getGameById(gameId); // Получаем игру по ID
        if (game != null) {
            game.play(); // Запускаем игру
            model.addAttribute("message", "Вы прошли игру!");
        } else {
            model.addAttribute("message", "Игра не найдена!");
        }
        return "gameResult"; // Отображаем результат игры
    }
}
