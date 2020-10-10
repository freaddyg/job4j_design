package ru.job4j.collection;

import java.util.*;

public class SimpleList<T> implements Iterable<T> {
    private Node<T> first;
    private Node<T> last;
    private int size = 0;
    private int modCount = 0;

    public SimpleList() {

    }

    public void add(T value) {
        final Node<T> link = last;
        final Node<T> newNode = new Node<>(value, link, null, size);
        last = newNode;
        if (link == null) {
            first = newNode;
        } else {
            link.next = newNode;
        }
        size++;
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(0, size - index);
        Node<T> el = first;
        for (int i = 0; i < size; i++) {
            if (el.i == index) {
                return el.item;
            } else {
                el = el.next;
            }
        }
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int count = 0;
            private int expectedModCount = modCount;
            private Node<T> elem;

            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                elem = first;
                first = first.next;
                count++;
                return elem.item;
            }
        };
    }

    private class Node<T> {
        private T item;
        private Node<T> prev;
        private Node<T> next;
        private int i;

        Node(T item, Node<T> prev, Node<T> next, int i) {
            this.item = item;
            this.next = next;
            this.prev = prev;
            this.i = i;
        }
    }
}

