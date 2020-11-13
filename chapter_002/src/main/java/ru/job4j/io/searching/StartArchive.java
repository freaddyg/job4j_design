package ru.job4j.io.searching;



import java.util.List;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class StartArchive {
    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        ArgZip check = new ArgZip(args);
        if (check.valid()) {
            List<Path> arch = Searching.search(Paths.get(check.directory()), check.exclude());
            zip.packFiles(arch, Paths.get(check.output()));
        }
    }
}
