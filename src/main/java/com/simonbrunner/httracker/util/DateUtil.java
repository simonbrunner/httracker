package com.simonbrunner.httracker.util;

import org.apache.commons.lang3.time.FastDateFormat;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static final String DATE_PATTERN = "dd.MM.yyyy HH:mm";
    public static final String DATETIME_PATTERN = "dd.MM.yyyy HH:mm";

    public static final FastDateFormat DATETIME_FORMAT = FastDateFormat.getInstance(DATETIME_PATTERN);
    public static final FastDateFormat DATE_FORMAT = FastDateFormat.getInstance(DATE_PATTERN);

    private DateUtil() {
    }

    public static Date createDate(int year, int month, int day) {
        return createTimestamp(year, month, day, 0, 0);
    }

    public static Date createTimestamp(int year, int month, int day, int hours, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(0);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hours);
        calendar.set(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }

}
