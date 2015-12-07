package com.simonbrunner.httracker.util;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DateUtilTest {
    @Test
    public void testDate() {
        Date testDate = DateUtil.createDate(1977, 7, 14);

        String formattedDate = DateUtil.DATE_FORMAT.format(testDate);

        assertThat(formattedDate, is("14.07.1977"));
    }

    @Test
    public void testDateTime() {
        Date testDate = DateUtil.createTimestamp(1977, 7, 14, 12, 45);

        String formattedDate = DateUtil.DATETIME_FORMAT.format(testDate);

        assertThat(formattedDate, is("14.07.1977 12:45"));
    }
}
