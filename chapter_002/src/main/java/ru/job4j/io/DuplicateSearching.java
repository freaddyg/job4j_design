package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DuplicateSearching extends SimpleFileVisitor<Path> {
    private List<String> list = new ArrayList<>();
    private Set<String> unic = new HashSet<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        list.add(file.toFile().getName() + file.toFile().length());
        return FileVisitResult.CONTINUE;
    }

    public List<String> getDuplicate() {
        List<String> res = new ArrayList<>();
        for (String f : list) {
            if (!unic.add(f)) {
                res.add(f);
            }
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        Path start = Paths.get(".");
        DuplicateSearching searcher = new DuplicateSearching();
        Files.walkFileTree(start, searcher);
        List<String> res = searcher.getDuplicate();
        System.out.println(res);
    }
}
