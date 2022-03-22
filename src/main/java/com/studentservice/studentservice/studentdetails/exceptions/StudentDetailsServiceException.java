package com.studentservice.studentservice.studentdetails.exceptions;


import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
public class StudentDetailsServiceException extends RuntimeException{

    private String message;
    private String error;
    private Date date;

    public StudentDetailsServiceException(String message, String error, Date date){
        super(message);
        this.message = message;
        this.error = error;
        this.date= date;
    }

}
