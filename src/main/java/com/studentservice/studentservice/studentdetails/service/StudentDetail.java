package com.studentservice.studentservice.studentdetails.service;

import com.studentservice.studentservice.studentdetails.data.StudentDetails;
import com.studentservice.studentservice.studentdetails.data.StudentDetailsResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface StudentDetail {

    public StudentDetailsResponse addStudentDetails(String registrationNumber, StudentDetails studentDetails);

    public StudentDetails getStudentDetails(String registrationNumber);

    public StudentDetailsResponse updateStudentDetails(String registrationNumber, StudentDetails studentDetails);

    public List<StudentDetailsResponse> getStudentsByRankAndStandardAndSection(Integer rank, Integer standard, String section,String range);

    public List<StudentDetailsResponse> getStudentsByPercentageAndStandard(Float percentage,String range, Integer standard);

    public List<StudentDetailsResponse> getStudentsBySubjectAndMarksRange(String subject, Integer marks, String range);


}
