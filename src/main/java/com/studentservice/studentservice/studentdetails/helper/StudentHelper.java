package com.studentservice.studentservice.studentdetails.helper;

import com.studentservice.studentservice.studentcurd.data.StudentData;
import com.studentservice.studentservice.studentdetails.data.StudentDetails;
import com.studentservice.studentservice.studentdetails.data.StudentDetailsResponse;
import com.studentservice.studentservice.studentdetails.exceptions.StudentDetailsServiceException;
import com.studentservice.studentservice.studentdetails.mappers.AllStudentDetailsMappers;
import com.studentservice.studentservice.studentdetails.repo.StudentDetailsRepository;
import com.studentservice.studentservice.studentdetails.validators.StudentDetailValidators;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
public class StudentHelper {

    @Autowired
    private StudentDetailValidators studentDetailValidators;

    public Map<String,String> getRegistrationNumberAndName(@NotNull List<StudentData> sData) throws StudentDetailsServiceException{
        Map<String,String> registrationNumberList = new HashMap<>();
        if (sData.isEmpty()){
            throw new StudentDetailsServiceException(AllStudentDetailsMappers.STUDENT_LIST_EMPTY.getMessage(), AllStudentDetailsMappers.STUDENT_LIST_EMPTY.getError(), new Date());
        }
       for (StudentData studentData : sData){
           registrationNumberList.put(studentData.getRegistrationID(), studentData.getFirstName() + studentData.getLastName());
       }
       return registrationNumberList;
    }

    //to-do update the logic
    public List<StudentDetailsResponse> getStudentsOnRank(List <StudentDetails> studentDetails,Map<String, String> registrationNumberList, Integer rank, String range) throws StudentDetailsServiceException{
        StudentDetailsResponse studentDetailResponse = new StudentDetailsResponse();
        List<StudentDetailsResponse> sDetailResponseList = new ArrayList<>();
        for (StudentDetails sDetails : studentDetails ){
            if(studentDetailValidators.isPresent(registrationNumberList,sDetails.getRegistrationNumber())){
                if (studentDetailValidators.isRankHolder(sDetails)){
                    if (studentDetailValidators.rangeChecker(sDetails,rank,range)){
                        studentDetailResponse.setMessage("Found");
                        studentDetailResponse.setName(registrationNumberList.get(sDetails.getRegistrationNumber()));
                        studentDetailResponse.setRegistrationNumber(sDetails.getRegistrationNumber());
                        studentDetailResponse.setRank(sDetails.getExamMarksData().getRank());
                        sDetailResponseList.add(studentDetailResponse);
                    }
                    throw new StudentDetailsServiceException(AllStudentDetailsMappers.STUDENT_NOT_FOUND_WITH_GIVEN_RANK.getMessage(), AllStudentDetailsMappers.STUDENT_NOT_FOUND_WITH_GIVEN_RANK.getError(), new Date());
                }
                    throw new StudentDetailsServiceException(AllStudentDetailsMappers.NO_RANKHOLDER_STUDENT_FOUND.getMessage(),AllStudentDetailsMappers.STUDENT_NOT_FOUND_WITH_GIVEN_RANK.getError(), new Date());
            }
        }
        return sDetailResponseList;
    }

    public List<StudentDetailsResponse> getStudentOnPercentage(List<StudentDetails> studentDetails,Float percentage, String range) throws StudentDetailsServiceException{
        List<StudentDetailsResponse> sDetailsList = new ArrayList<StudentDetailsResponse>();
        StudentDetailsResponse sDetailResponse = new StudentDetailsResponse();
        for (StudentDetails sDetails : studentDetails) {
            if (studentDetailValidators.percentageRangeChecker(sDetails, percentage, range))
                sDetailResponse.setMessage("Found");
            sDetailResponse.setRegistrationNumber(sDetails.getRegistrationNumber());
            sDetailResponse.setPercentage(sDetails.getExamMarksData().getPercentage());
            sDetailsList.add(sDetailResponse);
        }
            return sDetailsList;

        }

    public List<StudentDetailsResponse> getStudentSubjectAndMarks(List<StudentDetails> studentDetails, Integer marks, String range,String subject) throws StudentDetailsServiceException {
        List<StudentDetailsResponse> sDetailsList = new ArrayList<StudentDetailsResponse>();
        StudentDetailsResponse sDetailResponse = new StudentDetailsResponse();
        for (StudentDetails sDetails : studentDetails){
            if (studentDetailValidators.subjectAndMarksRangeChecker(sDetails, marks, range, subject))
                sDetailResponse.setMessage("Found");
        sDetailResponse.setRegistrationNumber(sDetails.getRegistrationNumber());
        sDetailResponse.setSubject(subject);
        sDetailResponse.setMarks(sDetails.getExamMarksData().getTotalMarks().get(subject));
        sDetailsList.add(sDetailResponse);
    }
        return sDetailsList;
    }


}
