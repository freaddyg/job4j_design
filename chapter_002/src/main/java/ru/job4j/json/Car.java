package ru.job4j.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONPropertyIgnore;

import java.util.Arrays;

public class Car {
    private String model;
    private Engine eng;
    private int run;
    private boolean ready;
    private String[] repairWork;

    public Car(String model, int run, boolean ready, String... repairWork) {
        this.model = model;
        this.run = run;
        this.ready = ready;
        this.repairWork = repairWork;
    }

    public static void main(String[] args) {
        Car car = new Car("Kia Optima", 110000, true, "Покраска", "Замена фильтров");
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(car));

        final String carJson =
                "{"
                        + "\"model\":\"Kia Optima\","
                        + "\"eng\":"
                        + "{"
                        + "\"power\":\"180\""
                        + "},"
                        + "\"run\":110000,"
                        + "\"ready\":true,"
                        + "\"repairWork\":"
                        + "[\"Покраска\",\"Замена фильтров\"]"
                        + "}";
        final Car carMod = gson.fromJson(carJson, Car.class);
        System.out.println(carMod);
    }

    @Override
    public String toString() {
        return "Car{"
                + "model='" + model + '\''
                + ", eng=" + eng
                + ", run=" + run
                + ", ready=" + ready
                + ", repairWork=" + Arrays.toString(repairWork)
                + '}';
    }

    public String getModel() {
        return model;
    }

    public Engine getEng() {
        return eng;
    }

    public int getRun() {
        return run;
    }

    public boolean isReady() {
        return ready;
    }

    public String[] getRepairWork() {
        return repairWork;
    }

    public void setEng(Engine eng) {
        this.eng = eng;
    }
}
