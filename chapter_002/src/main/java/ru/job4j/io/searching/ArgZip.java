package ru.job4j.io.searching;

import java.nio.file.Files;
import java.nio.file.Paths;

public class ArgZip {
    private final String[] args;

    public ArgZip(String[] args) {
        this.args = args;
    }

    public boolean valid() {
        boolean result = true;
        if (!Files.isDirectory(Paths.get(directory()))) {
            result = false;
        }
        if (!exclude().matches("[a-z]+")) {
            result = false;
        }
        if (!output().contains("zip")) {
            result = false;
        }
        return result;
    }

    public String directory() {
        return args.length > 2 ? args[1] : "NO CORRECT";
    }

    public String exclude() {
        return args.length > 4 ? args[3] : "NO CORRECT";
    }

    public String output() {
        return args.length > 5 ? args[5] : "NO CORRECT";
    }
}
