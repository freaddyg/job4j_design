package ru.job4j.collection;

import org.junit.Test;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AnalizeTest {
    @Test
    public void whenMultiDelete() {
        List<Analize.User> prev = List.of(new Analize.User(1, "Yuriy"),
                                            new Analize.User(2, "Andrey"),
                                            new Analize.User(3, "Maks"),
                                            new Analize.User(4, "Evgeniy"));

        List<Analize.User> curr = List.of(new Analize.User(2, "Fedor"),
                                            new Analize.User(3, "Igor"),
                                            new Analize.User(4, "Ig"),
                                            new Analize.User(5, "Elena"));
        Analize analize = new Analize();
        Analize.Info inf = analize.diff(prev, curr);

        assertThat(inf.added, is(1));
        assertThat(inf.changed, is(3));
        assertThat(inf.deleted, is(1));
    }

}