package com.simonbrunner.httracker.boundary;

import com.simonbrunner.httracker.HtTrackerApplication;
import com.simonbrunner.httracker.control.AggregatedValueControl;
import com.simonbrunner.httracker.entity.AggregatedValue;
import com.simonbrunner.httracker.entity.MeasurementType;
import com.simonbrunner.httracker.repository.AggregatedValueRepository;
import com.simonbrunner.httracker.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(HtTrackerApplication.class)
public class FileCreationSchedulerIT {

    private static final Logger log = LoggerFactory.getLogger(FileCreationSchedulerIT.class);

    @Autowired
    FileCreationScheduler objectUnderTest;

    @Autowired
    AggregatedValueRepository aggregatedValueRepository;

    @Test
    public void createDataFile_UsingEmptyDatabase() {
        aggregatedValueRepository.deleteAll();

        // Run against an empty database
        objectUnderTest.createDataFile();
    }

    @Test
    public void exportData() {
        // Setup some test data
        setupTemperatureData();
        setupHumidityData();

        // Run the method under test
        objectUnderTest.createDataFile();
    }

    private void setupTemperatureData() {
        addAggregatedValues(2015, 12, 1, 8.0, 10.0, 14.5, MeasurementType.TEMPERATURE);
        addAggregatedValues(2015, 12, 2, 8.1, 11.0, 14.0, MeasurementType.TEMPERATURE);
        addAggregatedValues(2015, 12, 3, 7.0, 9.0, 12.0, MeasurementType.TEMPERATURE);
        addAggregatedValues(2015, 12, 4, 7.5, 9.5, 11.8, MeasurementType.TEMPERATURE);
        addAggregatedValues(2015, 12, 5, 6.8, 10.0, 11.5, MeasurementType.TEMPERATURE);
    }

    private void setupHumidityData() {
        addAggregatedValues(2015, 12, 1, 58.0, 60.0, 63.5, MeasurementType.HUMIDITY);
        addAggregatedValues(2015, 12, 2, 59.1, 62.0, 63.0, MeasurementType.HUMIDITY);
        addAggregatedValues(2015, 12, 3, 59.0, 61.0, 62.0, MeasurementType.HUMIDITY);
        addAggregatedValues(2015, 12, 4, 59.5, 60.5, 61.8, MeasurementType.HUMIDITY);
        addAggregatedValues(2015, 12, 5, 60.8, 61.0, 64.5, MeasurementType.HUMIDITY);
    }

    private void addAggregatedValues(int year, int month, int day, Double min, Double average, Double max, MeasurementType measurementType) {
        AggregatedValue aggregatedValue = new AggregatedValue(DateUtil.createTimestamp(year, month, day, 0, 0), min, average, max, measurementType);
        aggregatedValueRepository.save(aggregatedValue);
    }
}
