package ru.job4j.collection;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {
    private int size = 0;
    private int modCount = 0;
    private Object[] store = new Object[0];

    public T get(int index) {
        Objects.checkIndex(0, size);
        return (T) store[index];
    }

    public void add(T model) {
        if (size == store.length) {
            store = Arrays.copyOf(store, size + 1);
        }
        store[size++] = model;
        modCount++;
    }

    public void remove(int index) {
        Objects.checkIndex(0, size);
        System.arraycopy(store, index + 1, store, index, size - index - 1);
        store[store.length - 1] = null;
        size--;
        modCount++;
    }

    @Override
    public Iterator<T> iterator() {
        return new SimpleArrayIt<>();
    }

    private class SimpleArrayIt<T> implements Iterator<T> {
        private int count = 0;
        private int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return count < store.length;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            return  (T) store[count++];
        }
    }
}
