package com.simonbrunner.httracker.control;

import com.simonbrunner.httracker.HtTrackerApplication;
import com.simonbrunner.httracker.entity.MeasuredValue;
import com.simonbrunner.httracker.entity.MeasurementType;
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
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(HtTrackerApplication.class)
// @ContextConfiguration(classes = {MeasuredValueControl.class, MeasuredValueRepository.class})
public class MeasuredValueControlIT {

    private static final Logger log = LoggerFactory.getLogger(MeasuredValueControlIT.class);

    @Autowired
    MeasuredValueControl objectUnderTest;

    @Autowired
    MeasuredValueRepository measuredValueRepository;

    List<MeasuredValue> measuredValues = new ArrayList<>();

    @Before
    public void init() {
        measuredValues.add(new MeasuredValue(DateUtil.createTimestamp(2015, 12, 1, 0, 0), 10.5d, MeasurementType.TEMPERATURE));
        measuredValues.add(new MeasuredValue(DateUtil.createTimestamp(2015, 12, 1, 1, 0), 10d, MeasurementType.TEMPERATURE));
        measuredValues.add(new MeasuredValue(DateUtil.createTimestamp(2015, 12, 1, 2, 0), 9.5d, MeasurementType.TEMPERATURE));
        measuredValues.add(new MeasuredValue(DateUtil.createTimestamp(2015, 12, 1, 3, 0), 9d, MeasurementType.TEMPERATURE));
        measuredValues.add(new MeasuredValue(DateUtil.createTimestamp(2015, 12, 1, 4, 0), 8.5d, MeasurementType.TEMPERATURE));
        measuredValues.add(new MeasuredValue(DateUtil.createTimestamp(2015, 12, 1, 5, 0), 8d, MeasurementType.TEMPERATURE));
        measuredValues.add(new MeasuredValue(DateUtil.createTimestamp(2015, 12, 1, 6, 0), 8.5d, MeasurementType.TEMPERATURE));
        measuredValues.add(new MeasuredValue(DateUtil.createTimestamp(2015, 12, 1, 7, 0), 9d, MeasurementType.TEMPERATURE));
        measuredValues.add(new MeasuredValue(DateUtil.createTimestamp(2015, 12, 1, 8, 0), 10d, MeasurementType.TEMPERATURE));
        measuredValues.add(new MeasuredValue(DateUtil.createTimestamp(2015, 12, 1, 9, 0), 10d, MeasurementType.TEMPERATURE));
        measuredValues.add(new MeasuredValue(DateUtil.createTimestamp(2015, 12, 1, 10, 0), 10.5d, MeasurementType.TEMPERATURE));
        measuredValues.add(new MeasuredValue(DateUtil.createTimestamp(2015, 12, 1, 11, 0), 11d, MeasurementType.TEMPERATURE));
        measuredValues.add(new MeasuredValue(DateUtil.createTimestamp(2015, 12, 1, 12, 0), 11.5d, MeasurementType.TEMPERATURE));
        measuredValues.add(new MeasuredValue(DateUtil.createTimestamp(2015, 12, 1, 13, 0), 12d, MeasurementType.TEMPERATURE));
        measuredValues.add(new MeasuredValue(DateUtil.createTimestamp(2015, 12, 1, 14, 0), 12.5d, MeasurementType.TEMPERATURE));
        measuredValues.add(new MeasuredValue(DateUtil.createTimestamp(2015, 12, 1, 15, 0), 13d, MeasurementType.TEMPERATURE));
        measuredValues.add(new MeasuredValue(DateUtil.createTimestamp(2015, 12, 1, 16, 0), 12.5d, MeasurementType.TEMPERATURE));
        measuredValues.add(new MeasuredValue(DateUtil.createTimestamp(2015, 12, 1, 17, 0), 12d, MeasurementType.TEMPERATURE));
        measuredValues.add(new MeasuredValue(DateUtil.createTimestamp(2015, 12, 1, 18, 0), 12d, MeasurementType.TEMPERATURE));
        measuredValues.add(new MeasuredValue(DateUtil.createTimestamp(2015, 12, 1, 19, 0), 11.5d, MeasurementType.TEMPERATURE));
        measuredValues.add(new MeasuredValue(DateUtil.createTimestamp(2015, 12, 1, 20, 0), 11d, MeasurementType.TEMPERATURE));
        measuredValues.add(new MeasuredValue(DateUtil.createTimestamp(2015, 12, 1, 21, 0), 11d, MeasurementType.TEMPERATURE));
        measuredValues.add(new MeasuredValue(DateUtil.createTimestamp(2015, 12, 1, 22, 0), 10.5d, MeasurementType.TEMPERATURE));
        measuredValues.add(new MeasuredValue(DateUtil.createTimestamp(2015, 12, 1, 23, 0), 10.5d, MeasurementType.TEMPERATURE));

        for (MeasuredValue measuredValue : measuredValues) {
            log.info("Persisting entity {}", measuredValue);
            measuredValueRepository.save(measuredValue);
        }
    }

    @Test
    public void condenseDailyValues() {
        Iterable<MeasuredValue> persistedValues = measuredValueRepository.findAll();
        for (MeasuredValue persistedValue : persistedValues) {
            log.info("Loaded {} item from database: {}", MeasuredValue.class.getSimpleName(), persistedValue);
        }
    }


}
