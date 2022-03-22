package com.studentservice.studentservice.multiplemongoconfig;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MultipleMongoConfig {


    @Primary
    @Bean(name = "studentproperties")
    @ConfigurationProperties(prefix = "spring.data.mongodb.student")
    public MongoProperties  getStudentDbProp()throws Exception{
        return  new MongoProperties();
    }

    @Bean(name = "studentdetailsproperties")
    @ConfigurationProperties(prefix = "spring.data.mongodb.studentdetails")
    public MongoProperties  getStudentDetailsDbProp()throws Exception{
        return  new MongoProperties();
    }

    @Primary
    @Bean(name = "studentMongoTemplate")
    public MongoTemplate newStudentMongoTemplate() throws Exception{
        return new MongoTemplate(studentMongoDataBaseFactory(getStudentDbProp()));
    }

    @Bean(name = "studentdetailsMongoTemplate")
    public MongoTemplate newStudentDetailsMongoTemplate() throws Exception{
        return new MongoTemplate(studentDetailsMongoDataBaseFactory(getStudentDetailsDbProp()));
    }

    @Primary
    @Bean
    public MongoDatabaseFactory studentMongoDataBaseFactory(MongoProperties studentDbProp) throws Exception{
        return new SimpleMongoClientDatabaseFactory(
                studentDbProp.getUri()
        );
    }

    @Bean
    public MongoDatabaseFactory studentDetailsMongoDataBaseFactory(MongoProperties studentDetailsDbProp) throws Exception{
        return new SimpleMongoClientDatabaseFactory(
                studentDetailsDbProp.getUri()
        );
    }

}
