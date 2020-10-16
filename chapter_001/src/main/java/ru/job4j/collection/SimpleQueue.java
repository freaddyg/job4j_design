package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.pushFirst(in.popFirst());
            }
        }
        return out.popFirst();
    }

    public void push(T value) {
        in.pushFirst(value);
    }
}
