package com.simonbrunner.httracker.repository;

import com.simonbrunner.httracker.entity.MeasuredValue;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface MeasuredValueRepository extends CrudRepository<MeasuredValue, Long> {

    MeasuredValue findByMeasurementTimestamp(Date measurementTimestamp);

}
