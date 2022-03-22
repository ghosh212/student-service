package com.studentservice.studentservice.studentdetails.repo;

import com.studentservice.studentservice.studentdetails.data.StudentDetails;
import com.studentservice.studentservice.studentdetails.service.StudentDetail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface StudentDetailsRepository extends MongoRepository<StudentDetails,String> {

    @Query("{'rank':?0,'standard':?1,'section':?2}")
    List<StudentDetails> findStudentsByRankAndStandardAndSection(Integer rank, Integer standard, String section);

    @Query("{'registrationNumber':?0}")
    StudentDetails findStudentDetailsByRegistrationNumber(String registrationNumber);

    @Query(value = "{'registrationNumber':?0}")
    List<StudentDetails> findStudentByMultipleRegistration(List<String> registrationNumber);



}
