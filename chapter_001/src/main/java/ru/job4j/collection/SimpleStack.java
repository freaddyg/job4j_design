package ru.job4j.collection;

import java.util.ArrayList;
import java.util.List;

public class SimpleStack<T> {
    private ForwardLinked<T> linked = new ForwardLinked<T>();

    public T popFirst() {
        return linked.deleteFirst();
    }

    public boolean isEmpty() {
        return !linked.iterator().hasNext();
    }

    public void pushFirst(T value) {
        linked.addFirst(value);
    }
}
