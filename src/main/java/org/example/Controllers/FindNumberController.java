package org.example.Controllers;

import org.example.Services.FindNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FindNumberController {

    private static final String MESSAGE_KEY = "message";
    private static final String NUMBER_TO_GUESS_KEY = "numberToGuess";
    private static final String ATTEMPTS_KEY = "attempts";
    private static final String GUESS_KEY = "guess";

    @Autowired
    private FindNumberService findNumberService;

    @GetMapping("/findnumber")
    public String showGamePage(Model model) {
        int numberToGuess = findNumberService.generateNumberToGuess();
        int attempts = 0;

        model.addAttribute(NUMBER_TO_GUESS_KEY, numberToGuess);
        model.addAttribute(ATTEMPTS_KEY, attempts);
        model.addAttribute(GUESS_KEY, 0);
        model.addAttribute(MESSAGE_KEY, "Поздравляю! Вы нашли число");

        return "findNumberGame";
    }

    @PostMapping("/findnumber")
    public String checkGuess(@RequestParam("guess") int guess,
                             @RequestParam("numberToGuess") int numberToGuess,
                             @RequestParam("attempts") int attempts,
                             Model model) {

        String message = findNumberService.checkGuess(guess, numberToGuess, attempts);

        if (message.contains("Поздравляю")) {
            // Если угадали, то заново генерируем новое число
            return showGamePage(model);
        }

        // Если еще не угадали, обновляем модель
        model.addAttribute(MESSAGE_KEY, message);
        model.addAttribute(NUMBER_TO_GUESS_KEY, numberToGuess);
        model.addAttribute(ATTEMPTS_KEY, findNumberService.increaseAttempts(attempts));
        model.addAttribute(GUESS_KEY, guess);

        return "findNumberGame";
    }
}
