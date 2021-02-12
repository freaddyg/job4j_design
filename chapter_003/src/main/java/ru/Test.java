package ru;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static List<A> a = new ArrayList<>();
    public static List<B> b = new ArrayList<>();
    public static List<C> c = new ArrayList<>();
    public static void func (List<? extends C> list) {
        for (C el : list) {
            System.out.println(el);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        a.add(new A());
        func(a);
    }
}
