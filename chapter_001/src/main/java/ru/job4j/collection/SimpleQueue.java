package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int sizeIn = 0;
    private int sizeOut = 0;

    public T poll() {
        if (sizeOut == 0) {
            while (sizeIn != 0) {
                out.pushFirst(in.popFirst());
                sizeIn--;
                sizeOut++;
            }
        }
        sizeOut--;
        return out.popFirst();
    }

    public void push(T value) {
        in.pushFirst(value);
        sizeIn++;
    }
}
