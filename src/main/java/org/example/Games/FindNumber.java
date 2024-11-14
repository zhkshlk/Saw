package org.example.Games;

import org.example.Game;

import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class FindNumber implements Game {
    Random random = new Random();
    Scanner scanner = new Scanner(System.in);
    int numberToGuess = random.nextInt(100) + 1;
    int attempts = 0;
    int guess = 0;

    @Override
    public void play() throws NoSuchElementException {
        System.out.println("Я загадал число от 1 до 100. Попробуйте угадать!");
        while (guess != numberToGuess) {
            System.out.println("Ваше предложение!");
            guess = scanner.nextInt();
            attempts++;
            if (guess < numberToGuess) {
                System.out.println("Мое число больше");
            }
            else if (guess > numberToGuess) {
                System.out.println("Мое число меньше");
            }
            else {
                System.out.println("Вы угадали. Количество попыток " + attempts);
            }
        }
    }
}

