package com.studentservice.studentservice.studentcurd.service;

import com.studentservice.studentservice.studentcurd.mappers.AllMappers;
import com.studentservice.studentservice.studentcurd.data.StudentData;
import com.studentservice.studentservice.studentcurd.data.StudentDataResponse;
import com.studentservice.studentservice.studentcurd.exception.StudentServiceException;
import com.studentservice.studentservice.studentcurd.repo.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository sRepo;

    @Override
    public StudentDataResponse addStudent(StudentData studentData) {
        log.debug("Inserting Student Data in DB");
        if (ObjectUtils.isEmpty(studentData)){
            log.debug("Student Data Empty");
            //threow exception
        }
        StudentData sData = sRepo.insert(studentData);
        if (ObjectUtils.isEmpty(sData)){
            //throw exception
        }
        StudentDataResponse sDataResponse = new StudentDataResponse();
        sDataResponse.setFirstname(sData.getFirstName());
        sDataResponse.setLastName(sData.getLastName());
        sDataResponse.setRegistrationNumber(sData.getRegistrationID());
        sDataResponse.setMessage("Student Data Insert Successfull");
        return sDataResponse;
    }

    //to-do soft delete
    @Override
    public StudentDataResponse deleteStudent(String registrationNumber) {
        log.debug("Inserting Student Data in DB");
        if (ObjectUtils.isEmpty(registrationNumber)){
            log.debug("Student Data Empty");
            //threow exception
        }
        Optional<StudentData> sData = sRepo.findById(registrationNumber);
        if (ObjectUtils.isEmpty(sData)){
            //throw exception
        }
      //  sRepo.is
       // sDataResponse.setFirstname(sData.getFirstName());
      //  sDataResponse.setLastName(sData.getLastName());
      //  sDataResponse.setRegistrationNumber(sData.getRegistrationID());
      //  sDataResponse.setMessage("Student Data Insert Successfull");
     //   return sDataResponse;
        return null;
    }

    @Override
    public StudentDataResponse updateStudent(String registrationNumber, StudentData studentData) {
        log.debug("Searching Student Data in DB");
        Optional<StudentData> sData = sRepo.findById(registrationNumber);
        if (!sData.isPresent()){
            throw new StudentServiceException(AllMappers.STUDENT_NOT_FOUND.getMessage(),AllMappers.STUDENT_NOT_FOUND.getError(), new Date());
        }
        StudentData studentNewData = sRepo.save(studentData);
        StudentDataResponse sDataResponse = new StudentDataResponse();
        sDataResponse.setFirstname(studentNewData.getFirstName());
        sDataResponse.setLastName(studentNewData.getLastName());
        sDataResponse.setRegistrationNumber(studentNewData.getRegistrationID());
        sDataResponse.setMessage("UPDATE SUCCESSFULL");
        return sDataResponse;
    }

    @Override
    public Optional<StudentData> getSingleStudent(String registrationNumber) {
        log.debug("Searching Student Data in DB");
        Optional<StudentData> sData = sRepo.findById(registrationNumber);
        if (!sData.isPresent()){
            throw new StudentServiceException(AllMappers.STUDENT_NOT_FOUND.getMessage(),AllMappers.STUDENT_NOT_FOUND.getError(), new Date());
        }
        return sData;
    }

    @Override
    public List<StudentData> getStudentByStandardAndSection(String section, Integer standard) {
        log.debug("Searching Student Data in DB");
        List<StudentData> studentListData = sRepo.findAllStudentByStandardAndSection(section,standard);
        if (studentListData.isEmpty()){
            throw new StudentServiceException(AllMappers.STUDENT_NOT_FOUND.getMessage(), AllMappers.STUDENT_NOT_FOUND.getError(), new Date());
        }
        return studentListData;
    }

    @Override
    public List<StudentData> getStudentByStandardAndSectionAndStream(String section, Integer standard, String plus12Stream) {
        log.debug("Searching Student Data in DB");
        List<StudentData> studentDataList = sRepo.findStudentByStandardAndSectionAndStream(section, standard, plus12Stream);
        if (studentDataList.isEmpty()) {
            throw new StudentServiceException(AllMappers.STUDENT_NOT_FOUND.getMessage(), AllMappers.STUDENT_NOT_FOUND.getError(), new Date());
        }
        return studentDataList;
    }
}
