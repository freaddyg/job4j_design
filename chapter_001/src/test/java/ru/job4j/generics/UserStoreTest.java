package ru.job4j.generics;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.it.EventIt;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserStoreTest {

    @Test
    public void add() {
        Store users = new UserStore();
        Base us = new User("1");
        users.add(us);
        String exp = "1";
        String res = users.findById("1").getId();
        assertThat(res, is(exp));
    }

    @Test
    public void replace() {
        Store users = new UserStore();
        Base us = new User("1");
        users.add(us);
        boolean res = users.replace("1", new User("3"));
        assertThat(res, is(true));
    }

    @Test
    public void delete() {
        Store users = new UserStore();
        Base us = new User("1");
        users.add(us);
        boolean res = users.delete("1");
        assertThat(res, is(true));
    }

    @Test
    public void findById() {
        Store users = new UserStore();
        Base us = new User("1");
        users.add(us);
        Base res = users.findById("1");
        assertThat(res, is(us));
    }
}