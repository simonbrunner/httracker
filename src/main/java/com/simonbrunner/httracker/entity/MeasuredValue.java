package com.simonbrunner.httracker.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class MeasuredValue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date measurementTimestamp;

    @Column(nullable = false)
    private Double value;

    @Enumerated
    @Column(nullable = false)
    private MeasurementType type;

    public MeasuredValue() {
        super();
    }

    public MeasuredValue(Date measurementTimestamp, Double value, MeasurementType type) {
        super();

        this.measurementTimestamp = measurementTimestamp;
        this.value = value;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getMeasurementTimestamp() {
        return measurementTimestamp;
    }

    public void setMeasurementTimestamp(Date measurementTimestamp) {
        this.measurementTimestamp = measurementTimestamp;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public MeasurementType getType() {
        return type;
    }

    public void setType(MeasurementType type) {
        this.type = type;
    }
}
