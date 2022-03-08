package com.studentservice.studentservice.exception;

import java.util.Date;

public class StudentControllerException extends  RuntimeException{
    public String errorMessage;

    public String error;

    public Date date;

    public StudentControllerException(String errorMessage, String error, Date date){
        this.errorMessage = errorMessage;
        this.error = error;
        this.date = date;

    }
}
