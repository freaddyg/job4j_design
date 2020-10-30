package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

public class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;

    Tree(final E root) {
        this.root = new Node<>(root);
    }

    private Optional<Node<E>> searchByPredicate(Predicate<Node<E>> searching) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (searching.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        var p = findBy(parent);
        var ch = findBy(child);
        if (p.isPresent() && !ch.isPresent()) {
            p.get().children.add(new Node<E>(child));
                rsl = true;
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
            return searchByPredicate(el -> el.value.equals(value));
    }

    @Override
    public boolean isBinary() {
        Optional<Node<E>> res = searchByPredicate(el -> el.children.size() > 2);
        return !res.isPresent();
    }
}
