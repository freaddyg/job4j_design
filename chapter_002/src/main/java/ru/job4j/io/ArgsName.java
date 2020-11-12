package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("must be at least 1 argument");
        }
        Stream.of(args).map(p -> p.substring(1)).forEach(p -> {
                                                        String[] res = p.split("=");
                                                        values.put(res[0], res[1]);
                                                    });
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("encoding"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
