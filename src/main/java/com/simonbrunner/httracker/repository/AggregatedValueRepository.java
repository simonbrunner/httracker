package com.simonbrunner.httracker.repository;

import com.simonbrunner.httracker.entity.AggregatedValue;
import com.simonbrunner.httracker.entity.MeasuredValue;
import com.simonbrunner.httracker.entity.MeasurementType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AggregatedValueRepository extends CrudRepository<AggregatedValue, Long> {

    List<AggregatedValue> findByTypeOrderByDayAsc(MeasurementType type);
}
