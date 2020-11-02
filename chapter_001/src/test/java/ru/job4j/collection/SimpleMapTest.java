package ru.job4j.collection;

import org.junit.Test;
import ru.job4j.model.Calendar;
import ru.job4j.model.User;


import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;

public class SimpleMapTest {
    @Test
    public void whenInsertStringKey() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        boolean rsl1 = map.insert("one", 1);
        boolean rsl2 = map.insert("two", 2);
        assertThat(rsl1, is(true));
        assertThat(rsl2, is(true));
    }

    @Test
    public void whenInsertUserKey() {
        SimpleMap<User, String> map = new SimpleMap<>();
        User user1 = new User("Yuriy", 0, null);
        User user2 = new User("Yuriy", 2, null);
        User user3 = new User("Yuriy", 1, null);
        User user4 = new User("Yuriy", 3, null);
        User user5 = new User("Yuriy", 0, null);

        assertTrue(map.insert(user1, "1"));
        assertTrue(map.insert(user2, "2"));
        assertTrue(map.insert(user3, "3"));
        assertTrue(map.insert(user4, "4"));
        assertFalse(map.insert(user5, "5"));

        assertThat(map.get(user2), is("2"));
    }

    @Test
    public void whenGettUserKey() {
        SimpleMap<User, String> map = new SimpleMap<>();
        User user1 = new User("Yuriy", 0, null);
        User user2 = new User("Yuriy", 2, null);
        User user3 = new User("Yuriy", 1, null);
        User user4 = new User("Yuriy", 3, null);

        map.insert(user1, "1");
        map.insert(user2, "2");
        map.insert(user3, "3");
        map.insert(user4, "4");

        assertThat(map.get(user4), is("4"));
    }

    @Test
    public void whenDeletetUserKey() {
        SimpleMap<User, String> map = new SimpleMap<>();
        User user1 = new User("Yuriy", 0, null);
        User user2 = new User("Yuriy", 2, null);
        User user3 = new User("Yuriy", 1, null);
        User user4 = new User("Yuriy", 3, null);

        map.insert(user1, "1");
        map.insert(user2, "2");
        map.insert(user3, "3");
        map.insert(user4, "4");

        assertTrue(map.delete(user1));
        assertNull(map.get(user1));
    }

    @Test
    public void whenIterable() {
        SimpleMap<User, String> map = new SimpleMap<>();
        User user1 = new User("Yuriy", 0, null);
        User user2 = new User("Yuriy", 2, null);
        User user3 = new User("Yuriy", 1, null);
        User user4 = new User("Yuriy", 3, null);

        map.insert(user1, "1");
        map.insert(user2, "2");
        map.insert(user3, "3");
        map.insert(user4, "4");

        Iterator it = map.iterator();
        assertTrue(it.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNoSuchElException() {
        SimpleMap<User, String> map = new SimpleMap<>();
        User user1 = new User("Yuriy", 0, null);
        User user2 = new User("Yuriy", 2, null);
        User user3 = new User("Yuriy", 1, null);
        User user4 = new User("Yuriy", 3, null);

        map.insert(user1, "1");
        map.insert(user2, "2");
        map.insert(user3, "3");
        map.insert(user4, "4");

        Iterator it = map.iterator();
        it.next();
        it.next();
        it.next();
        it.next();
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCurrModException() {
        SimpleMap<User, String> map = new SimpleMap<>();
        User user1 = new User("Yuriy", 0, null);
        User user2 = new User("Yuriy", 2, null);
        User user3 = new User("Yuriy", 1, null);
        User user4 = new User("Yuriy", 3, null);

        map.insert(user1, "1");
        map.insert(user2, "2");
        map.insert(user3, "3");

        Iterator it = map.iterator();
        map.insert(user4, "4");
        it.next();
        it.next();
        it.next();
    }
}