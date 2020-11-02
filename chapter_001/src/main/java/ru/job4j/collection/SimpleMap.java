package ru.job4j.collection;

import org.w3c.dom.ls.LSOutput;
import ru.job4j.model.User;

import java.util.*;

public class SimpleMap<K, V> implements Iterable<V> {
    private static final int CAPACITY = 16;
    private static final float LOADFACTOR = 0.75f;
    private Object[] store = new Object[CAPACITY];
    private int size = 0;
    private int modCount = 0;

    public boolean insert(K key, V value) {
        int index = hash(key, store.length);
        if (!Objects.equals(store[index], null)) {
            return false;
        }
        store[index] = value;
        modCount++;
        size++;
        if (size == store.length * LOADFACTOR) {
            Object[] newStore = resize();
            store = Arrays.copyOf(newStore, newStore.length);
        }
        return true;
    }

    public V get(K key) {
        int index = hash(key, store.length);
        return store[index] != null ? (V) store[index] : null;
    }

    public boolean delete(K key) {
        int index = hash(key, store.length);
        if (Objects.equals(store[index], null)) {
            return false;
        }
        store[index] = null;
        size--;
        modCount++;
        return true;
    }

    private Object[] resize() {
        Object[] newStore = new Object[CAPACITY * 2];
        int newIndex;
        for (Object el : store) {
            if (!Objects.equals(el, null)) {
                newIndex = hash((K) el, newStore.length);
                newStore[newIndex] = el;
            }
        }
        return newStore;
    }

    private int hash(K key, int length) {
        int hk = key == null ? 0 : key.hashCode();
        hk = hk ^ (hk >>> 16);
        return hk & (length - 1);
    }

    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {
            private int count = 0;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (count == store.length) {
                    return false;
                }
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
