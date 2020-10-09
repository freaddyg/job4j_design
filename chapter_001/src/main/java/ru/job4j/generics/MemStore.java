package ru.job4j.generics;

import java.util.List;
import java.util.ArrayList;

public final class MemStore<T extends Base> implements Store<T> {
    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        if (!mem.contains(model)) {
            mem.add(model);
        }
    }

    @Override
    public boolean replace(String id, T model) {
        for (int i = 0; i < mem.size(); i++) {
            if (mem.get(i).getId().equals(id)) {
                mem.set(i, model);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        for (int i = 0; i < mem.size(); i++) {
            if (mem.get(i).getId().equals(id)) {
                mem.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public T findById(String id) {
        for (T m : mem) {
            if (m.getId().equals(id)) {
                return m;
            }
        }
        return null;
    }
}
