package ru.job4j.collection;

import org.w3c.dom.ls.LSOutput;
import ru.job4j.model.User;

import java.util.*;

public class SimpleMap<K, V> implements Iterable<V> {
    private static final int CAPACITY = 16;
    private static final float LOADFACTOR = 0.75f;

    private Node<K, V>[] store = new Node[CAPACITY];

    private int size = 0;
    private int modCount = 0;

    public boolean insert(K key, V value) {
        int index = hash(key, store.length);
        boolean res = false;
        if (!Objects.equals(store[index], null) && Objects.equals(store[index].key, key)) {
            store[index] = new Node<>(index, key, value);
            res = true;
        } else if (Objects.equals(store[index], null)) {
            store[index] = new Node<>(index, key, value);
            modCount++;
            size++;
            if (size == store.length * LOADFACTOR) {
                store = resize();
            }
            res = true;
        }
        return res;
    }

    public V get(K key) {
        int index = hash(key, store.length);
        return store[index] != null && store[index].key.equals(key) ? store[index].value : null;
    }

    public boolean delete(K key) {
        boolean res = false;
        int index = hash(key, store.length);
        if (Objects.equals(store[index], null)) {
           return res;
        }
        if (store[index].key.equals(key)) {
            store[index] = null;
            size--;
            modCount++;
            res = true;
        }
        return res;
    }

    private Node<K, V>[] resize() {
        Node<K, V>[] newStore = new Node[CAPACITY * 2];
        int newIndex;
        for (Node<K, V> el : store) {
            if (!Objects.equals(el, null)) {
                newIndex = hash(el.key, newStore.length);
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

    private class Node<K, V> {
        private int hash;
        private K key;
        private V value;

        public Node(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }
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
