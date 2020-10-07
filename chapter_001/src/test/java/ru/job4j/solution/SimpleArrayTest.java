package ru.job4j.solution;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {

    @Test
    public void addTest() {
        SimpleArray<Integer> sA = new SimpleArray<>(5);
        sA.add(1);
        assertThat(sA.get(0), is(1));
    }

    @Test
    public void setTest() {
        SimpleArray<Integer> sA = new SimpleArray<>(5);
        sA.add(1);
        sA.add(2);
        sA.set(1, 3);
        assertThat(sA.get(1), is(3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeTest() {
        SimpleArray<Integer> sA = new SimpleArray<>(5);
        sA.add(1);
        sA.add(2);
        sA.remove(1);
        sA.get(1);
    }

    @Test
    public void getTest() {
        SimpleArray<Integer> sA = new SimpleArray<>(5);
        sA.add(1);
        int res = sA.get(0);
        assertThat(res, is(1));
    }
}