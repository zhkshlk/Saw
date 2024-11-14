package org.example.Games;
import org.example.Game;

import java.util.Scanner;

public class HanoiTower implements Game {
    private final int numDisks;
    private final char[] rods;
    private final int[] diskPosition;

    public HanoiTower(int numDisks) {
        this.numDisks = numDisks;
        this.rods = new char[]{'A', 'B', 'C'};
        this.diskPosition = new int[numDisks];

        for (int i = 0; i < numDisks; i++) {
            diskPosition[i] = 0;
        }
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        while (!isSolved()) {
            printState();
            System.out.print("Введите перемещение (например, 1 A B): ");
            String input = scanner.nextLine();
            String[] parts = input.split(" ");
            if (parts.length != 3) {
                System.out.println("Некорректный ввод. Попробуйте снова.");
                continue;
            }

            int diskNumber;
            try {
                diskNumber = Integer.parseInt(parts[0]) - 1;
            } catch (NumberFormatException e) {
                System.out.println("Некорректный номер диска. Попробуйте снова.");
                continue;
            }

            char fromRod = parts[1].toUpperCase().charAt(0);
            char toRod = parts[2].toUpperCase().charAt(0);
            if (makeMove(diskNumber, fromRod, toRod)) {
                System.out.println("Ход выполнен.");
            } else {
                System.out.println("Некорректный ход. Попробуйте снова.");
            }
        }
        System.out.println("Поздравляю! Вы решили задачу.");
    }

    private boolean isSolved() {
        return diskPosition[numDisks - 1] == 2;
    }

    private void printState() {
        System.out.println("Текущая позиция дисков:");
        for (int i = 0; i < numDisks; i++) {
            System.out.printf("Диск %d на стержне %c%n", i + 1, rods[diskPosition[i]]);
        }
    }

    private boolean makeMove(int diskNumber, char fromRod, char toRod) {
        if (diskNumber < 0 || diskNumber >= numDisks) return false;

        int fromIndex = getRodIndex(fromRod);
        int toIndex = getRodIndex(toRod);

        if (fromIndex == -1 || toIndex == -1) return false;
        if (diskPosition[diskNumber] != fromIndex) return false;

        for (int i = 0; i < numDisks; i++) {
            if (diskPosition[i] == toIndex) {
                if (diskNumber < i) return false;
                break;
            }
        }
        diskPosition[diskNumber] = toIndex;
        return true;
    }

    private int getRodIndex(char rod) {
        for (int i = 0; i < rods.length; i++) {
            if (rods[i] == rod) return i;
        }
        return -1;
    }
}





