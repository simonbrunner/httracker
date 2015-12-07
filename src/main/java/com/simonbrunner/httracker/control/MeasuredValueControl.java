package com.simonbrunner.httracker.control;


import com.simonbrunner.httracker.repository.MeasuredValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MeasuredValueControl {

    @Autowired
    private MeasuredValueRepository measuredValueRepository;
}
