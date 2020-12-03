package ru.job4j.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {

        /* JSONObject из json-строки строки */
        JSONObject jsonContact = new JSONObject("{\"phone\":\"+7(924)111-111-11-11\"}");

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("Car");
        list.add("Free");
        JSONArray jsonStatuses = new JSONArray(list);

        /* JSONObject напрямую методом put */
        final Engine eng = new Engine("180");
        final Car car = new Car("kia", 110000, true, "rep");
        car.setEng(eng);
        eng.setCar(car);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("model", car.getModel());
        jsonObject.put("run", car.getRun());
        jsonObject.put("engine", car.getEng());
        jsonObject.put("contact", jsonContact);
        jsonObject.put("statuses", jsonStatuses);

        /* Выведем результат в консоль */
        System.out.println(jsonObject.toString());

        /* Преобразуем объект car в json-строку */
        System.out.println(new JSONObject(car).toString());
    }
}
