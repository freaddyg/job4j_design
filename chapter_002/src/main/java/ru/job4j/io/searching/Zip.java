package ru.job4j.io.searching;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target.toString())))) {
                for (Path fp : sources) {
                    zip.putNextEntry(new ZipEntry(fp.toString()));
                    try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(fp.toString()))) {
                        zip.write(out.readAllBytes());
                    }
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Archived successfully");
    }

    public void packSingleFile(Path source, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target.toString())))) {
            zip.putNextEntry(new ZipEntry(source.toString()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toString()))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
