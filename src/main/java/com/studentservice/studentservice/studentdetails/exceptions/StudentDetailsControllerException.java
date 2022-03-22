package com.studentservice.studentservice.studentdetails.exceptions;

import java.util.Date;

public class StudentDetailsControllerException extends RuntimeException {

        public String errorMessage;
        public String error;
        public Date date;

        public StudentDetailsControllerException(String errorMessage, String error, Date date) {
            this.errorMessage = errorMessage;
            this.error = error;
            this.date = date;
        }

}
