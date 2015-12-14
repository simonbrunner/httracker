package com.simonbrunner.httracker.entity;

import com.simonbrunner.httracker.util.DateUtil;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.persistence.*;
import java.util.Date;

@Entity
public class AggregatedValue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date day;

    @Column(nullable = false)
    private Double average;

    @Column(nullable = false)
    private Double min;

    @Column(nullable = false)
    private Double max;

    @Enumerated
    @Column(nullable = false)
    private MeasurementType type;

    public AggregatedValue() {
        super();
    }

    public AggregatedValue(Date day, Double min, Double average, Double max, MeasurementType type) {
        super();

        this.day = day;
        this.min = min;
        this.average = average;
        this.max = max;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDay() {
        return day;
    }

    public String getDayFormattedForReport() {
        return DateUtil.formatDayForReport(day);
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public MeasurementType getType() {
        return type;
    }

    public void setType(MeasurementType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
