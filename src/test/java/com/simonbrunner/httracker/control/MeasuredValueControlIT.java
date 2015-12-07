package com.simonbrunner.httracker.control;

import com.simonbrunner.httracker.HtTrackerApplication;
import com.simonbrunner.httracker.entity.AggregatedValue;
import com.simonbrunner.httracker.entity.MeasuredValue;
import com.simonbrunner.httracker.entity.MeasurementType;
import com.simonbrunner.httracker.repository.AggregatedValueRepository;
import com.simonbrunner.httracker.repository.MeasuredValueRepository;
import com.simonbrunner.httracker.util.DateUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(HtTrackerApplication.class)
public class MeasuredValueControlIT {

    private static final Logger log = LoggerFactory.getLogger(MeasuredValueControlIT.class);

    @Autowired
    MeasuredValueControl objectUnderTest;

    @Autowired
    AggregatedValueRepository aggregatedValueRepository;
    @Autowired
    MeasuredValueRepository measuredValueRepository;

    @Test
    public void condenseData_UsingEmptyDatabase() {
        measuredValueRepository.deleteAll();

        // Run against an empty database
        objectUnderTest.condenseData(MeasurementType.TEMPERATURE);
    }

    @Test
    public void condenseData() {
        // Setup some test data
        createMeasuredValues(2015, 12, 1, 10.0, 1, MeasurementType.TEMPERATURE);
        createMeasuredValues(2015, 12, 2, 20.0, 1, MeasurementType.TEMPERATURE);
        createMeasuredValues(2015, 12, 3, 30.0, 1, MeasurementType.TEMPERATURE);
        // --> create values for today and expect them to not get aggregated
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        createMeasuredValues(year, month, day, 10.0, 1, MeasurementType.TEMPERATURE);

        // Run the method under test
        objectUnderTest.condenseData(MeasurementType.TEMPERATURE);

        // Verify the persisted values
        Iterable<AggregatedValue> aggregatedValues = aggregatedValueRepository.findAllByOrderByDayAsc();
        Double expectedAvg = 21.5;
        Double expectedMin = 10.0;
        Double expectedMax = 33.0;
        for (AggregatedValue aggregatedValue : aggregatedValues) {
            log.info("AggregatedValue loaded from database: {}", aggregatedValue);
            assertThat(aggregatedValue.getAverage(), is(expectedAvg));
            assertThat(aggregatedValue.getMin(), is(expectedMin));
            assertThat(aggregatedValue.getMax(), is(expectedMax));

            expectedAvg += 10;
            expectedMin += 10;
            expectedMax += 10;
        }
    }

    private void createMeasuredValues(int year, int month, int day, Double startValue, int increment, MeasurementType measurementType) {
        Double value = startValue;
        for (int hour = 0; hour < 24; hour++) {
            MeasuredValue measuredValue = new MeasuredValue(DateUtil.createTimestamp(year, month, day, hour, 0), value, measurementType);
            measuredValueRepository.save(measuredValue);
            value += increment;
        }
    }
}
