package org.example.Services;

import org.example.Game;
import org.springframework.stereotype.Service;

@Service
public class FindNumberService implements Game {

    public int generateNumberToGuess() {
        return (int) (Math.random() * 100) + 1;
    }

    public String checkGuess(int guess, int numberToGuess, int attempts) {
        attempts++;
        String message;

        if (guess < numberToGuess) {
            message = "Мое число больше!";
        } else if (guess > numberToGuess) {
            message = "Мое число меньше!";
        } else {
            message = "Поздравляю! Вы угадали число за " + attempts + " попыток!";
        }

        return message;
    }

    public int increaseAttempts(int attempts) {
        return attempts + 1;
    }
}
