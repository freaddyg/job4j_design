package ru.job4j.json;

import org.json.JSONPropertyIgnore;

public class Engine {
    private String power;
    private Car car;

    public Engine(String power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return "Engine{"
                + "power='" + power + '\''
                + '}';
    }

    @JSONPropertyIgnore
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
