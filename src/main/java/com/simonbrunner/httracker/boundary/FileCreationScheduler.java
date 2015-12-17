package com.simonbrunner.httracker.boundary;

import com.simonbrunner.httracker.control.AggregatedValueControl;
import com.simonbrunner.httracker.entity.MeasurementType;
import com.simonbrunner.httracker.util.PropertyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileCreationScheduler {

    private static final Logger log = LoggerFactory.getLogger(FileCreationScheduler.class);

    private static final String LINE_SEPARATOR = "\n";

    @Autowired
    private AggregatedValueControl aggregatedValueControl;
    @Autowired
    private PropertyUtil propertyUtil;

    @Scheduled(cron="0 15 0 * * *")
    public void createDataFile() throws Exception {
        StringBuilder fileContent = new StringBuilder();
        fileContent.append(LINE_SEPARATOR);
        fileContent.append(aggregatedValueControl.exportData(MeasurementType.TEMPERATURE));
        fileContent.append(LINE_SEPARATOR);
        fileContent.append(aggregatedValueControl.exportData(MeasurementType.HUMIDITY));
        fileContent.append(LINE_SEPARATOR);
        log.info("Generated data file: {}", fileContent.toString());

        String exportPathAsString = propertyUtil.getProperty("export.folder");
        log.info("Content persisted into file {}", exportPathAsString);
        Path exportPath = Paths.get(exportPathAsString);
        if (Files.notExists(exportPath)) {
            Files.createDirectory(exportPath);
        }
        Files.write(Paths.get(exportPathAsString + "/data.js"), fileContent.toString().getBytes());
    }
}
