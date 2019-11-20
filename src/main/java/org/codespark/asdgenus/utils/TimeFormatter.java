package org.codespark.asdgenus.utils;

public class TimeFormatter {

    private static TimeFormatter instance;

    private TimeFormatter() {
    }

    public static TimeFormatter getInstance() {
        if (instance == null)
            instance = new TimeFormatter();
        return instance;
    }

    public String getFormattedTime(String timeString) {

        float time = Float.parseFloat(timeString);
        int timeMin = (int) (time / 60);
        int timeSec = (int) (time % 60);
        return timeMin + "min " + timeSec + "sec";
    }
}
