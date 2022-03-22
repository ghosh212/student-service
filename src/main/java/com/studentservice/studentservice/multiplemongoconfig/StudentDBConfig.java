package com.studentservice.studentservice.multiplemongoconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {"com.studentservice.studentservice.studentcurd.repo"},
mongoTemplateRef =StudentDBConfig.MONGO_TEMPLATE )
public class StudentDBConfig {

    protected static final String MONGO_TEMPLATE= "studentMongoTemplate";
}
