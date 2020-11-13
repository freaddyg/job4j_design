package ru.job4j.io.searching;

import ru.job4j.io.SearchFiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Searching {
    public static List<Path> search(Path root, String ext) throws IOException {
        SearchVisitor searcher = new SearchVisitor(p -> p.toFile().getName().endsWith(ext));
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
