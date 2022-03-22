package com.studentservice.studentservice.studentcurd.exception;

import java.util.Date;

public class StudentServiceException extends RuntimeException{

    public String errorMessage;

    public String error;

    public Date date;

    public StudentServiceException(String errorMessage, String error, Date date){
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.error = error;
        this.date = date;

    }
}
