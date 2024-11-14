package org.example.Games;

import org.example.Game;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class A11 implements Game {

    @Override
    public void play () throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        FileReader file = new FileReader("a11.txt");
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            System.out.println(sc.nextLine());
        }

        int attempt = 6;
        while (attempt > 0) {
            int number = scanner.nextInt();
            if (number == 36) {
                System.out.println("Вы нашли правильный ответ");
                break;
            } else {
                attempt--;
                System.out.printf("Вы не правильно решили задачу, " +
                        "У вас осталось попыток: %s%n", attempt);
            }
        }
    }
}
