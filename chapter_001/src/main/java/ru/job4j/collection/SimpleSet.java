package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleSet<T> implements Iterable<T> {
    private SimpleArray<T> store = new SimpleArray<>();
    private int size = 0;

    public void add(T element) {
        if (!isValid(element)) {
            return;
        }
        store.add(element);
        size++;
    }

    private boolean isValid(T element) {
        if (checkNull() && Objects.equals(element, null)) {
            return false;
        }
        for (T el : store) {
            if (Objects.equals(el, null)) {
                continue;
            }
            if (el.hashCode() == element.hashCode() && Objects.equals(el, element)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkNull() {
        for (T el : store) {
            if (Objects.equals(el, null)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return store.iterator();
    }
}
