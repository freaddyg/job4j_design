package ru.job4j.collection;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleSetTest {
    @Test
    public void whenNoduplicate() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(2);
        Iterator<Integer> it = set.iterator();
        int rsl1 = it.next();
        assertThat(rsl1, is(1));
        int rsl2 = it.next();
        assertThat(rsl2, is(2));
    }

    @Test
    public void whenDuplicate() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(1);
        set.add(2);

        Iterator<Integer> it = set.iterator();
        int rsl1 = it.next();
        assertThat(rsl1, is(1));
        int rsl2 = it.next();
        assertThat(rsl2, is(2));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNoSuchElement() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(1);

        Iterator<Integer> it = set.iterator();
        it.next();
        it.next();
    }
}