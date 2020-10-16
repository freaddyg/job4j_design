package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public void revert() {
        Node prev = null;
        Node current = head;
        Node next = null;

        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        head = prev;
    }

    public void addFirst(T value) {
        if (head == null) {
            Node<T> node = new Node<T>(value, null);
            head = node;
            return;
        }
        Node<T> node = new Node<T>(value, head);
        head = node;
    }

    public T deleteLast() {
        if (head == null) {
            throw new NoSuchElementException();
        }

        Node<T> tail = head;
        if (head.next == null) {
            head = null;
            return tail.value;
        }
        while (tail.next.next != null) {
            tail = tail.next;
        }
        T res = tail.next.value;
        tail.next = null;
        return res;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> res = head;
        head = head.next;
        res.next = null;
        return res.value;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        private T value;
        private Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
