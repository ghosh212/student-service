package com.studentservice.studentservice.studentdetails.service;

import com.studentservice.studentservice.studentcurd.data.StudentData;
import com.studentservice.studentservice.studentcurd.repo.StudentRepository;
import com.studentservice.studentservice.studentdetails.data.StudentDetails;
import com.studentservice.studentservice.studentdetails.data.StudentDetailsResponse;
import com.studentservice.studentservice.studentdetails.exceptions.StudentDetailsServiceException;
import com.studentservice.studentservice.studentdetails.helper.StudentHelper;
import com.studentservice.studentservice.studentdetails.mappers.AllStudentDetailsMappers;
import com.studentservice.studentservice.studentdetails.repo.StudentDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.*;

@Component
@Slf4j
public class StudentDetailsService implements StudentDetail{

    @Autowired
    private StudentDetailsRepository studentDetailsRepository;

    @Autowired
    private StudentRepository sRepo;

    @Autowired
    private StudentHelper studentHelper;

    @Override
    public StudentDetailsResponse addStudentDetails(String registrationNumber, StudentDetails studentDetails) throws StudentDetailsServiceException {
        log.debug(" Addition of details");
        if (registrationNumber.isEmpty() || ObjectUtils.isEmpty(studentDetails)){
            throw new StudentDetailsServiceException(AllStudentDetailsMappers.STUDENT_DETAILS_EMPTY.getMessage(), AllStudentDetailsMappers.STUDENT_DETAILS_EMPTY.getError(), new Date());
        }
        studentDetails.setRegistrationNumber(registrationNumber);
        StudentDetails sDetails = studentDetailsRepository.insert(studentDetails);
        if (ObjectUtils.isEmpty(sDetails)){
               throw new StudentDetailsServiceException(AllStudentDetailsMappers.STUDENT_INSERT_NOT_SUCCESSFULL.getMessage(), AllStudentDetailsMappers.STUDENT_INSERT_NOT_SUCCESSFULL.getError(), new Date());
        }
        StudentDetailsResponse sResponse = new StudentDetailsResponse();
        sResponse.setMessage("STUDENT DETAILS SUCCESSFULLY ADDED");
        return  sResponse;
    }

    @Override
    public StudentDetails getStudentDetails(String registrationNumber) throws StudentDetailsServiceException {
        if (registrationNumber.isEmpty()){
            throw new StudentDetailsServiceException(AllStudentDetailsMappers.STUDENT_REGISTRATION_NUMBER_NOT_PROVIDED.getMessage(), AllStudentDetailsMappers.STUDENT_REGISTRATION_NUMBER_NOT_PROVIDED.getMessage(), new Date());
        }
        StudentDetails sDetails = studentDetailsRepository.findStudentDetailsByRegistrationNumber(registrationNumber);
        if (ObjectUtils.isEmpty(sDetails)){
            throw  new StudentDetailsServiceException(AllStudentDetailsMappers.STUDENT_DETAILS_NOT_FOUND.getMessage(), AllStudentDetailsMappers.STUDENT_DETAILS_NOT_FOUND.getError(),new Date());
        }
        return sDetails;
    }

    @Override
    public StudentDetailsResponse updateStudentDetails(String registrationNumber, StudentDetails studentDetails) throws StudentDetailsServiceException {
        StudentDetailsResponse sResponse = new StudentDetailsResponse();
        if (registrationNumber.isEmpty() || ObjectUtils.isEmpty(studentDetails)){
            throw new StudentDetailsServiceException(AllStudentDetailsMappers.STUDENT_DETAILS_EMPTY.getMessage(), AllStudentDetailsMappers.STUDENT_DETAILS_EMPTY.getError(), new Date());
        }
        StudentDetails sDetails = studentDetailsRepository.save(studentDetails);
        if (ObjectUtils.isEmpty(sDetails)){
            sResponse.setRegistrationNumber(registrationNumber);
            sResponse.setMessage("STUDENT DETAILS UPDATE NOT SUCCESSFULL");
        }else{
            sResponse.setRegistrationNumber(registrationNumber);
            sResponse.setMessage("STUDENT DETAILS UPDATE  SUCCESSFULL");
        }
        return sResponse;
    }

    @Override
    public List<StudentDetailsResponse> getStudentsByRankAndStandardAndSection(Integer rank, Integer standard, String section, String range) throws StudentDetailsServiceException {
        log.debug("Fetching Data");
         List <StudentData> studentData =sRepo.findAllStudentByStandardAndSection(section,standard);
         if (studentData.isEmpty()){
             throw new StudentDetailsServiceException(AllStudentDetailsMappers.STUDENT_DETAILS_NOT_FOUND.getMessage(), AllStudentDetailsMappers.STUDENT_LIST_EMPTY.getError(), new Date());
         }
        Map<String,String> registrationNumberList = studentHelper.getRegistrationNumberAndName(studentData);
        List <StudentDetails> studentDetailsData = studentDetailsRepository.findAll();
        List<StudentDetailsResponse> sDetails = studentHelper.getStudentsOnRank(studentDetailsData,registrationNumberList,rank,range);
        if (sDetails.isEmpty()){
            throw new StudentDetailsServiceException(AllStudentDetailsMappers.STUDENT_NOT_FOUND_WITH_GIVEN_RANK.getMessage(), AllStudentDetailsMappers.STUDENT_NOT_FOUND_WITH_GIVEN_RANK.getError(), new Date());
        }
        return sDetails;
    }

    @Override
    public List<StudentDetailsResponse> getStudentsByPercentageAndStandard(Float percentage, String range, Integer standard) throws StudentDetailsServiceException {
        log.debug("Fetching Student on basis of standard from student DB");
        List<StudentData> sDataList = sRepo.findStudentByStandard(standard);
        if (sDataList.isEmpty()){
            throw new StudentDetailsServiceException(AllStudentDetailsMappers.STUDENT_LIST_EMPTY.getMessage(), AllStudentDetailsMappers.STUDENT_LIST_EMPTY.getError(), new Date());
        }
        log.debug("FETCHING/MANUPULATING STUDENT ON PERCENTAGE");
        Map<String,String> studentRegistrationMap = studentHelper.getRegistrationNumberAndName(sDataList);
        List<String> registrationNumberList = new ArrayList<>(studentRegistrationMap.keySet());
        List<StudentDetails> sDetailsList = studentDetailsRepository.findStudentByMultipleRegistration(registrationNumberList);
        List<StudentDetailsResponse> sDetailsResponse = studentHelper.getStudentOnPercentage(sDetailsList,percentage,range);
        if (sDetailsResponse.isEmpty()){
            throw new StudentDetailsServiceException(AllStudentDetailsMappers.STUDENT_NOT_FOUND_WITH_GIVEN_PERCENTAGE.getMessage(),AllStudentDetailsMappers.STUDENT_NOT_FOUND_WITH_GIVEN_PERCENTAGE.getError(), new Date());
        }
        return null;
    }

    @Override
    public List<StudentDetailsResponse> getStudentsBySubjectAndMarksRange(String subject, Integer marks, String range, Integer standard) throws StudentDetailsServiceException {
        log.debug("Fetching Student on basis of standard from student DB");
        List<StudentData> sDataList = sRepo.findStudentByStandard(standard);
        if (sDataList.isEmpty()){
            throw new StudentDetailsServiceException(AllStudentDetailsMappers.STUDENT_LIST_EMPTY.getMessage(), AllStudentDetailsMappers.STUDENT_LIST_EMPTY.getError(), new Date());
        }
        Map<String,String> studentRegistrationMap = studentHelper.getRegistrationNumberAndName(sDataList);
        List<String> registrationNumberList = new ArrayList<>(studentRegistrationMap.keySet());
        List<StudentDetails> sDetailsList = studentDetailsRepository.findStudentByMultipleRegistration(registrationNumberList);

        log.debug("Fetching/manupulation data on basis on marks");

        return null;
    }
}
