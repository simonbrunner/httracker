package com.simonbrunner.httracker.util;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PropertyUtilTest {

    PropertyUtil propertyUtil;

    @Before
    public void init() {
        propertyUtil = new PropertyUtil();
        propertyUtil.init();
    }

    @Test
    public void getProperty() {
        String property = propertyUtil.getProperty("export.folder");

        assertThat(property, is("/var/tmp/httracker"));
    }


}
