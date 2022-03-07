package com.studentservice.studentservice.service;

import com.studentservice.studentservice.data.StudentData;
import com.studentservice.studentservice.data.StudentDataResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface StudentService {

    public StudentData addStudent(StudentData studentData);

    public StudentDataResponse deleteStudent(String registrationNumber);

    public StudentDataResponse updateStudent (String registrationNumber, StudentData studentData);

    public StudentData getSingleStudent(String registrationNumber);

    public List<StudentData> getStudentByStandardAndSection(String section, Integer standard);

    public List<StudentData> getStudentByStandardAndSectionAndStream(String section,Integer standard, String plus12Stream);




}
