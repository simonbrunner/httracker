package com.simonbrunner.httracker.control;

import com.simonbrunner.httracker.HtTrackerApplication;
import com.simonbrunner.httracker.entity.AggregatedValue;
import com.simonbrunner.httracker.entity.MeasuredValue;
import com.simonbrunner.httracker.entity.MeasurementType;
import com.simonbrunner.httracker.repository.AggregatedValueRepository;
import com.simonbrunner.httracker.repository.MeasuredValueRepository;
import com.simonbrunner.httracker.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(HtTrackerApplication.class)
public class AggregatedValueControlIT {

    private static final Logger log = LoggerFactory.getLogger(AggregatedValueControlIT.class);

    @Autowired
    AggregatedValueControl objectUnderTest;

    @Autowired
    AggregatedValueRepository aggregatedValueRepository;

    @Test
    public void exportData_UsingEmptyDatabase() {
        aggregatedValueRepository.deleteAll();

        // Run against an empty database
        objectUnderTest.exportData(MeasurementType.TEMPERATURE);
    }

    @Test
    public void exportData() {
        // Setup some test data
        addAggregatedValues(2015, 12, 1, 8.0, 10.0, 14.5, MeasurementType.TEMPERATURE);
        addAggregatedValues(2015, 12, 2, 8.1, 11.0, 14.0, MeasurementType.TEMPERATURE);
        addAggregatedValues(2015, 12, 3, 7.0, 9.0, 12.0, MeasurementType.TEMPERATURE);
        addAggregatedValues(2015, 12, 4, 7.5, 9.5, 11.8, MeasurementType.TEMPERATURE);
        addAggregatedValues(2015, 12, 5, 6.8, 10.0, 11.5, MeasurementType.TEMPERATURE);

        // Run the method under test
        objectUnderTest.exportData(MeasurementType.TEMPERATURE);
    }

    private void addAggregatedValues(int year, int month, int day, Double min, Double average, Double max, MeasurementType measurementType) {
        AggregatedValue aggregatedValue = new AggregatedValue(DateUtil.createTimestamp(year, month, day, 0, 0), min, average, max, measurementType);
        aggregatedValueRepository.save(aggregatedValue);
    }
}
