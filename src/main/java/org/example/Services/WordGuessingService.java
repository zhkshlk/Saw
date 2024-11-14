package org.example.Services;

import org.example.Game;
import org.springframework.stereotype.Service;

@Service
public class WordGuessingService {

    private String word = "metalabs";
    private StringBuilder guessed = new StringBuilder("_".repeat(word.length())); // Строка с угаданными буквами
    private int attempts = 10;

    public String checkGuess(char guess) {
        if (attempts <= 0) {
            return "Вы проиграли! Загаданное слово: " + word;
        }

        if (word.indexOf(guess) != -1) {
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == guess) {
                    guessed.setCharAt(i, guess);
                }
            }
            if (guessed.indexOf("_") == -1) {
                return "Поздравляю! Вы отгадали слово: " + word;
            }
        } else {
            attempts--;
            return "Неверно! Попыток осталось: " + attempts;
        }
        return "";
    }

    public String getGuessed() {
        return guessed.toString();
    }

    public int getAttempts() {
        return attempts;
    }

    public void resetGame() {
        guessed = new StringBuilder("_".repeat(word.length()));
        attempts = 10;
    }
}
