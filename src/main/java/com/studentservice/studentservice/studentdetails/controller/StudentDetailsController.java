package com.studentservice.studentservice.studentdetails.controller;

import com.studentservice.studentservice.studentcurd.data.StudentData;
import com.studentservice.studentservice.studentcurd.data.StudentDataResponse;
import com.studentservice.studentservice.studentcurd.exception.StudentControllerException;
import com.studentservice.studentservice.studentcurd.exception.StudentServiceException;
import com.studentservice.studentservice.studentcurd.mappers.AllMappers;
import com.studentservice.studentservice.studentdetails.data.StudentDetails;
import com.studentservice.studentservice.studentdetails.data.StudentDetailsResponse;
import com.studentservice.studentservice.studentdetails.exceptions.StudentDetailsControllerException;
import com.studentservice.studentservice.studentdetails.exceptions.StudentDetailsServiceException;
import com.studentservice.studentservice.studentdetails.mappers.AllStudentDetailsMappers;
import com.studentservice.studentservice.studentdetails.service.StudentDetail;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/studentdetails")
@Slf4j
@Tag(name = "STUDENT DETAIlS API", description = "Manage Students and their Details wrt exams,marks.")
public class StudentDetailsController {

    @Autowired
    private StudentDetail studentDetail;

    @Operation(summary = "Create new Student And Insert it into Student DB")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Newly Created Student Data with registration Number",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentDetailsResponse.class))}),
            @ApiResponse(responseCode = "417", description = "Error",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentDetailsControllerException.class))}),
    })
    @RequestMapping(value = "/addStudent/{registrationNumber}", method = RequestMethod.POST)
    public ResponseEntity<?> addStudentDetails(@RequestBody(required = true) StudentDetails sDetails, @RequestParam("registrationNumber") String registrationNumber) {
        if (ObjectUtils.isEmpty(sDetails)) {
            StudentDetailsControllerException sControllerException = new StudentDetailsControllerException(AllStudentDetailsMappers.STUDENT_DETAILS_EMPTY.getMessage(), AllStudentDetailsMappers.STUDENT_DETAILS_NOT_FOUND.getError(), new Date());
            return new ResponseEntity<StudentDetailsControllerException>(sControllerException, HttpStatus.EXPECTATION_FAILED);
        }
        try {
            StudentDetailsResponse sDetailResponse = studentDetail.addStudentDetails(registrationNumber,sDetails);
            return new ResponseEntity<StudentDetailsResponse>(sDetailResponse, HttpStatus.OK);
        } catch (StudentDetailsServiceException ex) {
            StudentDetailsControllerException sControllerException = new StudentDetailsControllerException(AllMappers.STUDENT_INSERT_UNSUCCESSFULL.getMessage(), AllMappers.STUDENT_INSERT_UNSUCCESSFULL.getError(), new Date());
            return new ResponseEntity<StudentDetailsControllerException>(sControllerException, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @Operation(summary = "Get Single Student Details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student Details",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentDetails.class))}),
            @ApiResponse(responseCode = "417", description = "Error",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentDetailsControllerException.class))}),
            @ApiResponse(responseCode = "404", description = "Error",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentDetailsControllerException.class))})
    })
    @RequestMapping(value = "/getSingleStudentDetails/{registrationNumber}", method = RequestMethod.GET)
    public ResponseEntity<?> getSingleStudent(@RequestParam("registrationNumber") String registrationNumber) {
        if (ObjectUtils.isEmpty(registrationNumber)) {
            StudentControllerException sControllerException = new StudentControllerException(AllStudentDetailsMappers.STUDENT_REGISTRATION_NUMBER_NOT_PROVIDED.getMessage(), AllStudentDetailsMappers.STUDENT_INSERT_NOT_SUCCESSFULL.getError(), new Date());
            return new ResponseEntity<StudentControllerException>(sControllerException, HttpStatus.EXPECTATION_FAILED);
        }
        try {
            StudentDetails sDataResponse = studentDetail.getStudentDetails(registrationNumber);
            return new ResponseEntity<StudentDetails>(sDataResponse, HttpStatus.OK);
        } catch (StudentDetailsServiceException ex) {
            StudentDetailsControllerException sControllerException = new StudentDetailsControllerException(AllMappers.STUDENT_INSERT_UNSUCCESSFULL.getMessage(), AllMappers.STUDENT_INSERT_UNSUCCESSFULL.getError(), new Date());
            return new ResponseEntity<StudentDetailsControllerException>(sControllerException, HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Update an existing Student details using registration Number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Newly Created Student Data with registration Number",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentDetailsResponse.class))}),
            @ApiResponse(responseCode = "417", description = "Error",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentDetailsControllerException.class))}),
    })
    @RequestMapping(value = "/updateStudentDetails/{registrationNumber}", method = RequestMethod.POST)
    public ResponseEntity<?> updateStudent(@RequestParam("registrationNumber") String registrationNumber,@RequestBody(required = true)StudentDetails sDetails) {
        if (ObjectUtils.isEmpty(sDetails)) {
            StudentDetailsControllerException sControllerException = new StudentDetailsControllerException(AllStudentDetailsMappers.STUDENT_DETAILS_EMPTY.getMessage(), AllStudentDetailsMappers.STUDENT_DETAILS_EMPTY.getError(), new Date());
            return new ResponseEntity<StudentDetailsControllerException>(sControllerException, HttpStatus.EXPECTATION_FAILED);
        }
        try {
            StudentDetailsResponse sDetailResponse = studentDetail.updateStudentDetails(registrationNumber,sDetails);
            return new ResponseEntity<StudentDetailsResponse>(sDetailResponse, HttpStatus.OK);
        } catch (StudentDetailsServiceException ex) {
            StudentDetailsControllerException sControllerException = new StudentDetailsControllerException(AllStudentDetailsMappers.STUDENT_INSERT_NOT_SUCCESSFULL.getMessage(), AllStudentDetailsMappers.STUDENT_INSERT_NOT_SUCCESSFULL.getError(), new Date());
            return new ResponseEntity<StudentDetailsControllerException>(sControllerException, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @Operation(summary = "Get All Student By Section And standard and rank on range")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get All Student By Section And Integer and Stream",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentDataResponse.class))}),
            @ApiResponse(responseCode = "417", description = "Error",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentControllerException.class))}),
    })
    @RequestMapping(value = "/getStudentDetails/{section}/{standard}/{rank}/{range}", method = RequestMethod.GET)
    public ResponseEntity<?> getStudentByStandardAndSectionAndRankAndRange(@RequestParam("section") String section, @RequestParam("standard") Integer standard, @RequestParam("range") String range,@RequestParam("rank") Integer rank) {
        if (ObjectUtils.isEmpty(section) ||ObjectUtils.isEmpty(standard) || ObjectUtils.isEmpty(range) || ObjectUtils.isEmpty(rank)) {
            StudentDetailsControllerException sControllerException = new StudentDetailsControllerException(AllMappers.SEARCH_CRITERIA_EMPTY.getMessage(), AllMappers.SEARCH_CRITERIA_EMPTY.getError(), new Date());
            return new ResponseEntity<StudentDetailsControllerException>(sControllerException, HttpStatus.EXPECTATION_FAILED);
        }
        try {
            List<StudentDetailsResponse> studentList = studentDetail.getStudentsByRankAndStandardAndSection(rank,standard,section,range);
            return new ResponseEntity<List<StudentDetailsResponse>>(studentList, HttpStatus.OK);
        } catch (StudentDetailsServiceException ex) {
            StudentDetailsControllerException sControllerException = new StudentDetailsControllerException(AllStudentDetailsMappers.STUDENT_LIST_EMPTY.getMessage(), AllStudentDetailsMappers.STUDENT_LIST_EMPTY.getError(), new Date());
            return new ResponseEntity<StudentDetailsControllerException>(sControllerException, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @Operation(summary = "Get All Student By percentage And standard on range")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get All Student By Section And Integer and Stream",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentDataResponse.class))}),
            @ApiResponse(responseCode = "417", description = "Error",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentControllerException.class))}),
    })
    @RequestMapping(value = "/getStudentDetails/{percentage}/{standard}/{range}", method = RequestMethod.GET)
    public ResponseEntity<?> getStudentByStandardAndSectionAndRankAndRange(@RequestParam("percentage") Float percentage, @RequestParam("standard") Integer standard, @RequestParam("range") String range) {
        if (ObjectUtils.isEmpty(percentage) ||ObjectUtils.isEmpty(standard) || ObjectUtils.isEmpty(range)) {
            StudentDetailsControllerException sControllerException = new StudentDetailsControllerException(AllMappers.SEARCH_CRITERIA_EMPTY.getMessage(), AllMappers.SEARCH_CRITERIA_EMPTY.getError(), new Date());
            return new ResponseEntity<StudentDetailsControllerException>(sControllerException, HttpStatus.EXPECTATION_FAILED);
        }
        try {
            List<StudentDetailsResponse> studentList = studentDetail.getStudentsByPercentageAndStandard(percentage,range,standard);
            return new ResponseEntity<List<StudentDetailsResponse>>(studentList, HttpStatus.OK);
        } catch (StudentDetailsServiceException ex) {
            StudentDetailsControllerException sControllerException = new StudentDetailsControllerException(AllStudentDetailsMappers.STUDENT_LIST_EMPTY.getMessage(), AllStudentDetailsMappers.STUDENT_LIST_EMPTY.getError(), new Date());
            return new ResponseEntity<StudentDetailsControllerException>(sControllerException, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @Operation(summary = "Get All Student By subject,marks And standard on range")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get All Student By Section And Integer and Stream",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentDataResponse.class))}),
            @ApiResponse(responseCode = "417", description = "Error",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentControllerException.class))}),
    })
    @RequestMapping(value = "/getStudentDetails/{subject}/{marks}/{standard}/{range}", method = RequestMethod.GET)
    public ResponseEntity<?> getStudentBySubjectAndMarksAndRange(@RequestParam("subject") String subject , @RequestParam("marks") Integer marks,  @RequestParam("standard") Integer standard,@RequestParam("range") String range) {
        if (ObjectUtils.isEmpty(subject) ||ObjectUtils.isEmpty(standard) || ObjectUtils.isEmpty(range) || ObjectUtils.isEmpty(standard)) {
            StudentDetailsControllerException sControllerException = new StudentDetailsControllerException(AllMappers.SEARCH_CRITERIA_EMPTY.getMessage(), AllMappers.SEARCH_CRITERIA_EMPTY.getError(), new Date());
            return new ResponseEntity<StudentDetailsControllerException>(sControllerException, HttpStatus.EXPECTATION_FAILED);
        }
        try {
            List<StudentDetailsResponse> studentList = studentDetail.getStudentsBySubjectAndMarksRange(subject,marks,range,standard);
            return new ResponseEntity<List<StudentDetailsResponse>>(studentList, HttpStatus.OK);
        } catch (StudentDetailsServiceException ex) {
            StudentDetailsControllerException sControllerException = new StudentDetailsControllerException(AllStudentDetailsMappers.STUDENT_LIST_EMPTY.getMessage(), AllStudentDetailsMappers.STUDENT_LIST_EMPTY.getError(), new Date());
            return new ResponseEntity<StudentDetailsControllerException>(sControllerException, HttpStatus.EXPECTATION_FAILED);
        }
    }
}
