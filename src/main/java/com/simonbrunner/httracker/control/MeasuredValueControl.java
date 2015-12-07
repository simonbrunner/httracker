package com.simonbrunner.httracker.control;


import com.simonbrunner.httracker.entity.MeasuredValue;
import com.simonbrunner.httracker.entity.MeasurementType;
import com.simonbrunner.httracker.entity.AggregatedValue;
import com.simonbrunner.httracker.repository.AggregatedValueRepository;
import com.simonbrunner.httracker.repository.MeasuredValueRepository;
import com.simonbrunner.httracker.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MeasuredValueControl {

    private static final Logger log = LoggerFactory.getLogger(MeasuredValueControl.class);

    @Autowired
    private AggregatedValueRepository aggregatedValueRepository;
    @Autowired
    private MeasuredValueRepository measuredValueRepository;

    public void condenseData(MeasurementType measurementType) {
        log.info("Condensing all data of type {}", measurementType);
        List<MeasuredValue> measuredValueList = measuredValueRepository.findByType(measurementType);
        log.info("{} items found for data condensation", measuredValueList.size());
        Map<String, Set<MeasuredValue>> measuredValueMap = sortValues(measuredValueList);

        // Now calculate the values per day (average, min and max)
        computeAggregatedValues(measurementType, measuredValueMap);
    }

    private void computeAggregatedValues(MeasurementType measurementType, Map<String, Set<MeasuredValue>> measuredValueMap) {
        for (String day : measuredValueMap.keySet()) {
            Double avg = 0.0d;
            Double min = Double.MAX_VALUE;
            Double max = Double.MIN_VALUE;
            Double total = 0.0;

            Set<MeasuredValue> measuredValuesPerDay = measuredValueMap.get(day);
            log.info("Condensing {} items all persisted on {}", measuredValuesPerDay.size(), day);

            for (MeasuredValue measuredValue : measuredValuesPerDay) {
                // Average
                total = total + measuredValue.getValue();
                // Min Value
                if (measuredValue.getValue() < min) {
                    min = measuredValue.getValue();
                }
                // Max Value
                if (measuredValue.getValue() > max) {
                    max = measuredValue.getValue();
                }
            }

            avg = total / measuredValuesPerDay.size();

            log.info("Average value on day {} = {}", avg, day);
            log.info("Min value on day {} = {}", min, day);
            log.info("Max value on day {} = {}", max, day);

            AggregatedValue aggregatedValue = new AggregatedValue();
            aggregatedValue.setDay(DateUtil.parseDay(day));
            aggregatedValue.setType(measurementType);
            aggregatedValue.setAverage(avg);
            aggregatedValue.setMin(min);
            aggregatedValue.setMax(max);
            aggregatedValueRepository.save(aggregatedValue);
        }
    }

    private Map<String, Set<MeasuredValue>> sortValues(List<MeasuredValue> measuredValueList) {
        // Sort the loaded values by day
        String today = DateUtil.formatDay(new Date());
        Map<String, Set<MeasuredValue>> measuredValueMap = new HashMap<>();
        for (MeasuredValue measuredValue : measuredValueList) {
            Date timestamp = measuredValue.getMeasurementTimestamp();
            String day = DateUtil.formatDay(timestamp);

            // Ignore all records measured today...
            if (!day.equals(today)) {
                Set<MeasuredValue> measuredValuesPerDay = measuredValueMap.get(day);
                if (measuredValuesPerDay == null) {
                    measuredValuesPerDay = new HashSet<>();
                    measuredValueMap.put(day, measuredValuesPerDay);
                }
                measuredValuesPerDay.add(measuredValue);
            }
        }
        log.info("Values sorted into independent sets for {} days", measuredValueMap.keySet().size());
        return measuredValueMap;
    }
}
