package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleSet<T> implements Iterable<T> {
    private SimpleArray<T> store = new SimpleArray<>();
    private int size = 0;

    public void add(T element) {
        for (T el : store) {
            if (el.hashCode() == element.hashCode() && el.equals(el)) {
                return;
            }
        }
        store.add(element);
        size++;
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
