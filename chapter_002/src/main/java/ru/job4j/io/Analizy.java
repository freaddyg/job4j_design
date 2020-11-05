package ru.job4j.io;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileOutputStream(target))) {

            List<String> store = new ArrayList<>();
            String serverStop = "";
            String subStr = "";
            while (read.ready()) {
                String s = read.readLine();
                if (serverStop.isEmpty() && (s.startsWith("400") || s.startsWith("500"))) {
                    subStr = s.substring(4);
                    serverStop = s;
                } else if (!serverStop.isEmpty() && (s.startsWith("200") || s.startsWith("300"))) {
                    store.add(subStr + ";" + s.substring(4));
                    serverStop = "";
                }
            }

            Stream.of(store).forEach(out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
