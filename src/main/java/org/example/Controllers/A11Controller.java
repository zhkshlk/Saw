package org.example.Controllers;

import org.example.Services.A11Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class A11Controller {

    @Autowired
    private A11Service a11Service;

    @GetMapping("/a11")
    public String showGamePage(Model model) {
        String fileContent = a11Service.getFileContent(); // Получаем содержимое текста из базы данных
        model.addAttribute("fileContent", fileContent);
        model.addAttribute("attempts", 6); // Начальное количество попыток
        return "a11Game";
    }

    @PostMapping("/a11")
    public String checkAnswer(@RequestParam("number") int number,
                              @RequestParam("attempts") int attempts,
                              Model model) {

        String message = a11Service.checkAnswer(number, attempts); // Проверяем ответ

        if (message.equals("Вы нашли правильный ответ!")) {
            // Если ответ правильный, можно сбросить количество попыток
            attempts = 6;
        } else {
            // Если неправильный ответ, уменьшаем количество попыток
            attempts = a11Service.decreaseAttempts(attempts);
        }

        model.addAttribute("message", message);
        model.addAttribute("fileContent", a11Service.getFileContent()); // Получаем содержимое текста из базы данных
        model.addAttribute("attempts", attempts);
        model.addAttribute("number", number);

        return "a11Game";
    }
}
