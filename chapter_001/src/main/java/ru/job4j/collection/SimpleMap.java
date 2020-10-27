package ru.job4j.collection;

import org.w3c.dom.ls.LSOutput;

import java.util.*;

public class SimpleMap<K, V> implements Iterable<V> {
    private Object[] store = new Object[10];
    private int size = 0;
    private int modCount = 0;

    public boolean insert(K key, V value) {
        int index = hash(key);
        if (store[index] != null) {
            return false;
        }
        store[index] = value;
        modCount++;
        size++;
        if (size == store.length) {
            store = Arrays.copyOf(store, store.length * 2);
        }
        return true;
    }

    public V get(K key) {
        int index = hash(key);
        return (V) store[index];
    }

    public boolean delete(K key) {
        int index = hash(key);
        if (store[index] == null) {
            return false;
        }
        store[index] = null;
        size--;
        modCount++;
        return true;
    }

    private int hash(K key) {
        int hk = key != null ? Objects.hashCode(key) % store.length : 0;
        return Math.abs(hk);
    }

    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {
            private int count = 0;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                while (store[count] == null) {
                    count++;
                }
                return count < store.length;
            }

            @Override
            public V next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return (V) store[count++];
            }
        };
    }
}
