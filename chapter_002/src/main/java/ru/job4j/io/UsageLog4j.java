package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        boolean bool = true;
        char ch = 'Y';
        short numShort = 1;
        int numInt = 33;
        long numLong = 65656;
        byte bt = 3;
        float numFloat = 99.9f;
        double numDouble = 1.1234;

        LOG.debug("info boll : {}, ch : {}, numShort : {}, numInt : {}", bool, ch, numShort, numInt);
        LOG.debug("info numLong : {}, bt : {}, numFloat : {}, numDouble : {}", numLong, bt, numFloat, numDouble);
    }
}
