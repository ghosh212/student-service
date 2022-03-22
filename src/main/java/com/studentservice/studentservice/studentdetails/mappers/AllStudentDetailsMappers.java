package com.studentservice.studentservice.studentdetails.mappers;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum AllStudentDetailsMappers {

    STUDENT_LIST_EMPTY("STUDENT LIST EMPTY","S-1"),
    STUDENT_DETAILS_NOT_FOUND("STUDENT DETAILS NOT FOUND", "S-2"),
    STUDENT_NOT_FOUND_WITH_GIVEN_RANK("STUDENT NOT FOUND WITH GIVEN RANK", "SNF-1"),
    STUDENT_NOT_FOUND_WITH_GIVEN_PERCENTAGE("STUDENT NOT FOUND GIVEN PERCENTAGE", "SNF-2"),
    NO_RANKHOLDER_STUDENT_FOUND("NO RANK HOLDER STUDENT FOUND","S-4"),
    STUDENT_DETAILS_EMPTY("STUDENT DETAILS NOT PROVIDED", "S-N-1"),
    STUDENT_REGISTRATION_NUMBER_NOT_PROVIDED("STUDENT REGISTRATION NUMBER NOT PROVIDED", "S-N-2"),
    STUDENT_INSERT_NOT_SUCCESSFULL("STUDENT INSERT NOT SUCCESSFUILL", "S-I-1");



    private String message;

    private String error;

     AllStudentDetailsMappers(String message,String error){
        this.error= error;
        this.message= message;
    }
}
