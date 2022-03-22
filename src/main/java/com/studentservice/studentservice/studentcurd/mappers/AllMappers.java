package com.studentservice.studentservice.studentcurd.mappers;

import lombok.Getter;
import org.springframework.stereotype.Service;

@Getter
public enum AllMappers {

    STUDENT_NOT_FOUND("STUDENT NOT FOUND. PLEASE GIVE CORRECT REGISTRATION NUMBER", "S-1"),
    STUDENT_DATA_EMPTY("STUDENT DATA IS EMPTY", "S-2"),
    STUDENT_INSERT_UNSUCCESSFULL("STUDENT DATA NOT INSERTED INTO DB", "S-I-1"),
    REGISTRATION_NUMBER_EMPTY("REGISTRATION NUMBER IS EMPTY","R-1"),
    SEARCH_CRITERIA_EMPTY("PLEASE PROVIDE VALID QUERY DETAILS", "search-1");

    private String message;

    private String error;

    AllMappers(String message, String error){
            this.message = message;
            this.error = error;

    }
}
