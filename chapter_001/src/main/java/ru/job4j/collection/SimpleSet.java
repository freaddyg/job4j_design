package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleSet<T> implements Iterable<T> {
    private SimpleArray<T> store = new SimpleArray<>();
    private int size = 0;

    public void add(T element) {
        if (contains(element)) {
            return;
        }
        store.add(element);
        size++;
    }

    private boolean contains(T element) {
        boolean res = false;
        for (T el : store) {
            if (Objects.equals(el, element)) {
                res = true;
            }
        }
        return res;
    }

    @Override
    public Iterator<T> iterator() {
        return store.iterator();
    }
}
