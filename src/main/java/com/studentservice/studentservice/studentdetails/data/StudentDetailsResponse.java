package com.studentservice.studentservice.studentdetails.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDetailsResponse {

    private String registrationNumber;
    private String name;
    private String message;
    private Integer rank;
    private float percentage;

}
