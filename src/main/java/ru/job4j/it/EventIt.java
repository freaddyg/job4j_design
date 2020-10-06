package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EventIt implements Iterator<Integer> {
    private int[] numbers;
    private int count = 0;
    private int count2 = 0;


    public EventIt(final int[] numbers) {
        this.numbers = numbers;
    }


    @Override
    public boolean hasNext() {
        for (int i = count; i < numbers.length; i++) {
            if (numbers[i] % 2 == 0) {
                count = i + 1;
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer next() {
      for (int i = count2; i < numbers.length; i++) {
          if (numbers[i] % 2 == 0) {
              count2 = i + 1;
              return numbers[i];
          }
      }
      throw new NoSuchElementException();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
