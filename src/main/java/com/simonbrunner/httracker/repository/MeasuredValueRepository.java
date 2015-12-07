package com.simonbrunner.httracker.repository;

import com.simonbrunner.httracker.entity.MeasuredValue;
import com.simonbrunner.httracker.entity.MeasurementType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MeasuredValueRepository extends CrudRepository<MeasuredValue, Long> {

    List<MeasuredValue> findByType(MeasurementType measurementType);

}
