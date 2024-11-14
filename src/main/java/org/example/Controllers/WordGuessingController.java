package org.example.Controllers;

import org.example.Services.WordGuessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WordGuessingController {

    @Autowired
    private WordGuessingService wordGuessingService;

    // Отображение начальной страницы игры
    @GetMapping("/wordguess")
    public String showGamePage(Model model) {
        model.addAttribute("guessed", wordGuessingService.getGuessed());
        model.addAttribute("attempts", wordGuessingService.getAttempts());
        model.addAttribute("message", "");
        return "wordGuessingGame";
    }

    // Проверка буквы, введенной пользователем
    @PostMapping("/wordguess")
    public String checkGuess(@RequestParam("guess") char guess, Model model) {
        // Получаем сообщение от сервиса, если есть, и обновляем состояние игры
        String message = wordGuessingService.checkGuess(guess);

        model.addAttribute("guessed", wordGuessingService.getGuessed());
        model.addAttribute("attempts", wordGuessingService.getAttempts());
        model.addAttribute("message", message);

        return "wordGuessingGame";
    }
}
