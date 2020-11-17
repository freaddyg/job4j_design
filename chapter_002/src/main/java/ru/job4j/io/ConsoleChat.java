package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;
    private boolean exit = false;
    private boolean stop = false;

    private List<String> answers;
    private List<String> logs = new ArrayList<>();
    private Scanner in;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    private List<String> readAnswer(String answers) {
        List<String> listAnswers = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(answers, Charset.forName("UTF-8")))) {
            listAnswers = read.lines().filter(str -> !str.isEmpty()).collect(Collectors.toList());
       } catch (IOException e) {
            e.printStackTrace();
        }
        return listAnswers;
    }

    private void writeLog(String logFile, String[] data) {
        try (BufferedWriter br = new BufferedWriter(
                new FileWriter(logFile, Charset.forName("UTF-8"), true))) {
            for (String line : data) {
                br.write(line);
                br.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        this.in = new Scanner(System.in);
        this.answers = readAnswer(this.botAnswers);
        while (!this.exit) {
            System.out.print("Enter your massage : ");
            String question = this.in.nextLine();
            System.out.println("User : " + question);
            this.logs.add("User : " + question);
            if (CONTINUE.equals(question) || OUT.equals(question)) {
                this.stop = false;
            }
            if (STOP.equals(question) || this.stop) {
                this.stop = true;
                continue;
            }
            if (OUT.equals(question)) {
                this.exit = true;
            } else {
                Random num = new Random();
                int randomAnswer = num.nextInt(this.answers.size());
                String answ = this.answers.get(randomAnswer);
                System.out.println("Вot : " + answ);
                this.logs.add("Bot : " + answ);
            }
        }
        writeLog(this.path, this.logs.toArray(new String[this.logs.size()]));
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./chatlogs.txt", "./answers.txt");
        cc.run();
    }
}
