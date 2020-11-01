package ru.job4j.collection;

import javax.swing.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Analize {

    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info();
        Map<Integer, String> store = new HashMap<>();
        for (User us : previous) {
            store.put(us.id, us.name);
        }
        for (User us : current) {
            if (!store.containsKey(us.id)) {
                info.added++;
            } else if (!store.get(us.id).equals(us.name)) {
                info.changed++;
            }
        }
        info.deleted = previous.size() - current.size() + info.added;
        return info;
    }

    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            User user = (User) o;
            return id == user.id && name.equals(user.name);
        }

        @Override
        public int hashCode() {
            int result = id;
            result = 31 * result + (name != null ? name.hashCode() : 0);
            return result;
        }
    }

    public static class Info {
        int added;
        int changed;
        int deleted;
    }
}
