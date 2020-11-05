package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Dir {
    public static long checkSize(File file) throws IOException {
        return Files.walk(file.toPath())
                .filter(p -> p.toFile().isFile())
                .mapToLong(p -> p.toFile().length())
                .sum();
    }

    public static void main(String[] args) throws IOException {
        File file = new File("c:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        System.out.println(String.format("name : %s - size: %s", file.getName(), file.getTotalSpace()));

        for (File subfile : file.listFiles()) {
            System.out.println(String.format("name : %s - size: %s", subfile.getName(), checkSize(subfile)));
        }
    }
}
