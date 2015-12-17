package com.simonbrunner.httracker.boundary;

import com.simonbrunner.httracker.control.AggregatedValueControl;
import com.simonbrunner.httracker.entity.MeasurementType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FileCreationScheduler {

    private static final Logger log = LoggerFactory.getLogger(FileCreationScheduler.class);

    private static final String LINE_SEPARATOR = "\n";

    @Autowired
    private AggregatedValueControl aggregatedValueControl;

    @Scheduled(cron="0 15 0 * * *")
    public void createDataFile() {
        StringBuilder fileContent = new StringBuilder();
        fileContent.append(LINE_SEPARATOR);
        fileContent.append(aggregatedValueControl.exportData(MeasurementType.TEMPERATURE));
        fileContent.append(LINE_SEPARATOR);
        fileContent.append(aggregatedValueControl.exportData(MeasurementType.HUMIDITY));
        fileContent.append(LINE_SEPARATOR);

        log.debug("Generated data file: {}", fileContent.toString());

        // Files.write(Paths.get("./data.js"), fileContent.toString().getBytes());
    }

}
