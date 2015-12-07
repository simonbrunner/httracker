package com.simonbrunner.httracker.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public enum MeasurementType {

    HUMIDITY,
    TEMPERATURE;
}
