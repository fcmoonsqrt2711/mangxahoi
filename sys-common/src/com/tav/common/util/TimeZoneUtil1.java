package com.tav.common.util;

import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class TimeZoneUtil1 {

    public static void main(String[] args) {

        String[] ids = TimeZone.getAvailableIDs();
        for (String id : ids) {
            System.out.println(displayTimeZone(TimeZone.getTimeZone(id)));
        }

        System.out.println("\nTotal TimeZone ID " + ids.length);

    }

    private static String displayTimeZone(TimeZone tz) {

        long hours = TimeUnit.MILLISECONDS.toHours(tz.getRawOffset());
        long minutes = TimeUnit.MILLISECONDS.toMinutes(tz.getRawOffset())
                - TimeUnit.HOURS.toMinutes(hours);
        // avoid -4:-30 issue
        minutes = Math.abs(minutes);

        if (hours > 0) {
            return String.format("(GMT+%d:%02d) %s", hours, minutes,
                    tz.getID());
        } else {
            return String.format("(GMT%d:%02d) %s", hours, minutes,
                    tz.getID());
        }

    }

}
