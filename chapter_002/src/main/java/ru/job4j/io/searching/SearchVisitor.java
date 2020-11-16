package ru.job4j.io.searching;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SearchVisitor extends SimpleFileVisitor<Path> {
    private Predicate<Path> searcher;
    private List<Path> paths = new ArrayList<>();

    public SearchVisitor(Predicate<Path> searcher) {
        this.searcher = searcher;
    }

    public List<Path> getPaths() {
        return paths;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (searcher.test(file)) {
            paths.add(file.toAbsolutePath());
        }
        return FileVisitResult.CONTINUE;
    }
}
