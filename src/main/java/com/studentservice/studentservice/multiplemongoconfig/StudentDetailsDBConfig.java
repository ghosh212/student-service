package com.studentservice.studentservice.multiplemongoconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {"com.studentservice.studentservice.studentdetails.repo"},
        mongoTemplateRef = StudentDetailsDBConfig.MONGO_TEMPLATE )
public class StudentDetailsDBConfig {

        protected static final String MONGO_TEMPLATE= "studentdetailsMongoTemplate";
    }


