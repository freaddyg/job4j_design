package ru.job4j.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public int getChildren() {
        return children;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + children;
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        return result;
    }

    public static void main(String[] args) {

        Map<User, Object> map = new HashMap<>();
        map.put(new User("Yuriy", 1, null), new Object());
        map.put(new User("Yuriy", 1, null), new Object());

        System.out.println(map.size());
        Set<User> users = map.keySet();
        for (User u : users) {
            System.out.println(u.getName() + " " + u.getChildren());
        }
    }
}
