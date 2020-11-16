package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;
    private boolean exit = false;
    private boolean stop = false;
    private Scanner in;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    private String readAnswer(String answers) {
        List<String> listAnswers = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(answers, Charset.forName("UTF-8")))) {
            listAnswers = read.lines().filter(str -> !str.isEmpty()).collect(Collectors.toList());
       } catch (IOException e) {
            e.printStackTrace();
        }
        Random num = new Random();
        int randomAnswer = num.nextInt(listAnswers.size());
        return listAnswers.get(randomAnswer);
    }

    private void writeLog(String logFile, String data) {
        try (BufferedWriter br = new BufferedWriter(
                new FileWriter(logFile, Charset.forName("UTF-8"), true))) {
            br.write(data + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        this.in = new Scanner(System.in);
        while (!this.exit) {
            System.out.print("Enter your massage : ");
            String question = this.in.nextLine();
            System.out.println("User : " + question);
            if (CONTINUE.equals(question) || OUT.equals(question)) {
                this.stop = false;
            }
            if (STOP.equals(question) || this.stop) {
                writeLog(this.path, "User : " + question);
                this.stop = true;
                continue;
            }
            if (OUT.equals(question)) {
                this.exit = true;
            } else {
                String answ = readAnswer(this.botAnswers);
                writeLog(this.path, "User : " + question + System.lineSeparator() + "Вot : " + answ);
                System.out.println("Вot : " + answ);
            }
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./chatlogs.txt", "./answers.txt");
        cc.run();
    }
}
