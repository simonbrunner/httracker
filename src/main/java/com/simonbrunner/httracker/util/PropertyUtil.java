package com.simonbrunner.httracker.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component
public class PropertyUtil {

    private static final Logger log = LoggerFactory.getLogger(PropertyUtil.class);

    private static final String APP_CONFIG = "application.properties";

    private Properties properties;

    @PostConstruct
    public void init() {
        InputStream inputStream = PropertyUtil.class.getClassLoader().getResourceAsStream(APP_CONFIG);
        properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            log.error("Failure while loading application properties", e);
        }
    }

    public String getProperty(String key) {
        return (String) properties.get(key);
    }
}
