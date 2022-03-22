package com.studentservice.studentservice.studentdetails.validators;

import com.studentservice.studentservice.studentdetails.data.StudentDetails;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface StudentDetailValidators {

    public boolean isPresent(Map<String,String> registrationNumber, String regNumber);

    public boolean isRankHolder(StudentDetails studentDetails);

    public boolean rangeChecker(StudentDetails studentDetails,Integer rank, String range);

    public boolean percentageRangeChecker(StudentDetails studentDetails,Float percentage, String range);

}
