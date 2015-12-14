package com.simonbrunner.httracker.control;


import com.simonbrunner.httracker.entity.AggregatedValue;
import com.simonbrunner.httracker.entity.MeasurementType;
import com.simonbrunner.httracker.repository.AggregatedValueRepository;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.StringWriter;
import java.net.URL;
import java.util.*;

@Component
public class AggregatedValueControl {

    private static final Logger log = LoggerFactory.getLogger(AggregatedValueControl.class);

    private static final String TEMPLATE_NAME = "data.js.ftl";

    @Autowired
    private AggregatedValueRepository aggregatedValueRepository;

    public void exportData(MeasurementType measurementType) {
        try {
            log.info("Exporting all data of type {}", measurementType);
            List<AggregatedValue> aggregatedValueList = aggregatedValueRepository.findByTypeOrderByDayAsc(measurementType);
            log.info("{} items found for data export", aggregatedValueList.size());

            // Create and adjust the configuration
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
            URL templateResource = AggregatedValueControl.class.getClassLoader().getResource(TEMPLATE_NAME);
            File templateDirectory = new File(templateResource.getFile()).getParentFile();
            cfg.setDirectoryForTemplateLoading(templateDirectory);
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

            // Setup the data model
            Map<String, Object> dataModel = new HashMap<>();
            dataModel.put("valueType", measurementType.name());
            dataModel.put("locationName", "LocationValue");
            dataModel.put("values", aggregatedValueList);

            // Generate the output file
            StringWriter out = new StringWriter();
            Template temp = cfg.getTemplate(TEMPLATE_NAME);
            temp.process(dataModel, out);

            log.info("Generated Javascript export: {}", out.getBuffer());
        } catch (Exception e) {
            throw new RuntimeException("Failure while creating Javascript data file", e);
        }
    }

}
