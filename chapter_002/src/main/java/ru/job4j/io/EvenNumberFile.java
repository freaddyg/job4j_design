package ru.job4j.io;

import java.io.FileInputStream;
import java.util.Scanner;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt");
             Scanner scanner = new Scanner(in)) {
            int read;
            while (scanner.hasNext()) {
                read = scanner.nextInt();
                if (read % 2 == 0) {
                    System.out.println(read);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
