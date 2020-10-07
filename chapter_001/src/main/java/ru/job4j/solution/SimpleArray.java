package ru.job4j.solution;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] container;
    private int count = 0;

    public SimpleArray(int num) {
        this.container = new Object[num];
    }

    public void add(T model) {
        container[count] = model;
        count++;
    }

    public void set(int index, T model) {
        index = Objects.checkIndex(index, count);
        container[index] = model;
    }

    public void remove(int index) {
        index = Objects.checkIndex(index, count);
        for (int i = index; i < container.length; i++) {
            container[index] = container[index + 1];
        }
        container[count - 1] = null;
        count--;
    }

    public T get(int index) {
        index = Objects.checkIndex(index, count);
        return (T) container[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new SimpleArrayIt<T>();
    }

    private class SimpleArrayIt<T> implements Iterator<T> {
        private int countIt = 0;

        @Override
        public boolean hasNext() {
            return countIt < container.length && container[countIt] != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) container[countIt++];
        }
    }
}
