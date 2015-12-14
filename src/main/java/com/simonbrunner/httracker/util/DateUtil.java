package com.simonbrunner.httracker.util;

import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    private static final Logger log = LoggerFactory.getLogger(DateUtil.class);

    public static final String DATE_PATTERN = "dd.MM.yyyy";
    public static final String DATETIME_PATTERN = "dd.MM.yyyy HH:mm";
    public static final String DATE_REPORT_PATTERN = "yyyyMMdd";

    public static final FastDateFormat DATE_FORMAT = FastDateFormat.getInstance(DATE_PATTERN);
    public static final FastDateFormat DATETIME_FORMAT = FastDateFormat.getInstance(DATETIME_PATTERN);
    public static final FastDateFormat DATE_REPORT_FORMAT = FastDateFormat.getInstance(DATE_REPORT_PATTERN);

    private DateUtil() {
    }

    public static String formatDay(Date date) {
        return DATE_FORMAT.format(date);
    }

    public static String formatDayForReport(Date date) {
        return DATE_REPORT_FORMAT.format(date);
    }

    public static Date parseDay(String date) {
        try {
            return DATE_FORMAT.parse(date);
        } catch (ParseException e) {
            log.error("Failure while parsing date {}: {}", date, e);
            throw new RuntimeException(e);
        }
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
