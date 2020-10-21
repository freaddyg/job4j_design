package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

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
        if (checkNull() && element == null) {
            return false;
        }
        for (T el : store) {
            if (el == null) {
                continue;
            }
            if (el.hashCode() == element.hashCode() && el.equals(element)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkNull() {
        for (T el : store) {
            if (el == null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int count = 0;

            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                return store.get(count++);
            }
        };
    }
}
