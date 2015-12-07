package com.simonbrunner.httracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HtTrackerApplication implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(HtTrackerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(HtTrackerApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {
        // log.info("Press Ctrl+C to shutdown application");
        // Thread.currentThread().join();
    }
}
