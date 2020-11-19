package ru.job4j.json;

public class Engine {
    private String power;

    public Engine(String power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return "Engine{"
                + "power='" + power + '\''
                + '}';
    }
}
