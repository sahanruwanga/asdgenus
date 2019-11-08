package org.codespark.asdgenus.utils;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class DateTimeGenerator {

    private static DateTimeGenerator instance;

    private DateTimeGenerator() {
    }

    public static DateTimeGenerator getInstance() {

        if (instance == null)
            instance = new DateTimeGenerator();
        return instance;
    }

    public String getCurrentDateTime() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return formatter.format(now);
    }
}
