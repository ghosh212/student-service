package com.studentservice.studentservice.studentdetails.validators;

import com.studentservice.studentservice.studentdetails.data.StudentDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class StudentValidatorHelper implements StudentDetailValidators{

    private final static String range_greater_equal = ">=";
    private final static String range_lesser_equal ="=<";
    private final static String range_equal = "=";
    private final static String range_lesser = "<";
    private final static String range_greater = ">";

    @Override
    public boolean isPresent(Map<String,String> registrationNumber, String regNumber) {
        List<String> regNum = new ArrayList<>(registrationNumber.keySet());
        Optional<String> found = regNum.stream().filter(rNumber -> rNumber.equals(regNumber)).findFirst();
        if (found.isPresent()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isRankHolder(StudentDetails studentDetails) {
        if (!studentDetails.getExamMarksData().isRankHolder()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean rangeChecker(StudentDetails studentDetails, Integer rank, String range) {
        if (range == range_equal){
            if (studentDetails.getExamMarksData().getRank() == rank){
                return true;
            }
        }else if (range == range_greater){
            if (studentDetails.getExamMarksData().getRank() > rank){
                return true;
            }
        }else if (range == range_lesser){
            if (studentDetails.getExamMarksData().getRank() < rank){
                return true;
            }
        }else if (range == range_greater_equal){
            if (studentDetails.getExamMarksData().getRank() >= rank){
                return true;
            }
        }else if (range == range_lesser_equal){
            if (studentDetails.getExamMarksData().getRank() <= rank){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean percentageRangeChecker(StudentDetails studentDetails,Float percentage, String range) {
        if (range == range_equal){
            if (studentDetails.getExamMarksData().getPercentage() == percentage){
                return true;
            }
        }else if (range == range_greater){
            if (studentDetails.getExamMarksData().getPercentage() > percentage){
                return true;
            }
        }else if (range == range_lesser){
            if (studentDetails.getExamMarksData().getPercentage() < percentage){
                return true;
            }
        }else if (range == range_greater_equal){
            if (studentDetails.getExamMarksData().getPercentage() >= percentage){
                return true;
            }
        }else if (range == range_lesser_equal) {
            if (studentDetails.getExamMarksData().getPercentage() <= percentage) {
                return true;
            }
        }
        return false;
    }
}
