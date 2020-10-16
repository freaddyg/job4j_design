package ru.job4j.collection;

import java.util.ArrayList;
import java.util.List;

public class SimpleStack<T> {
    private ForwardLinked<T> linked = new ForwardLinked<T>();

    public T pop() {
        return linked.deleteLast();
    }

    public T popFirst() {
        return linked.deleteFirst();
    }

    public void pushFirst(T value) {
        linked.addFirst(value);
    }

    public void push(T value) {
        linked.add(value);
    }
}
