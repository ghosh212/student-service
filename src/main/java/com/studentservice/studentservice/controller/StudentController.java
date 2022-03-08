package com.studentservice.studentservice.controller;

import com.studentservice.studentservice.Mappers.AllMappers;
import com.studentservice.studentservice.data.StudentData;
import com.studentservice.studentservice.data.StudentDataResponse;
import com.studentservice.studentservice.exception.StudentControllerException;
import com.studentservice.studentservice.exception.StudentServiceException;
import com.studentservice.studentservice.service.StudentServiceImpl;
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

@RequestMapping("/student")
@Slf4j
@Tag(name = "STUDENT API", description = "Manage Students and their details")
public class StudentController {

    @Autowired
    private StudentServiceImpl sService;

    @Operation(summary = "Create new Student And Insert it into Student DB")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Newly Created Student Data with registration Number",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentDataResponse.class))}),
            @ApiResponse(responseCode = "417", description = "Error",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentControllerException.class))}),
    })
    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
    public ResponseEntity<?> addStudent(@RequestBody(required = true)StudentData sData) {
        if (ObjectUtils.isEmpty(sData)) {
            StudentControllerException sControllerException = new StudentControllerException(AllMappers.STUDENT_DATA_EMPTY.getMessage(), AllMappers.STUDENT_DATA_EMPTY.getError(), new Date());
            return new ResponseEntity<StudentControllerException>(sControllerException, HttpStatus.EXPECTATION_FAILED);
        }
        try {
            StudentDataResponse sDataResponse = sService.addStudent(sData);
            return new ResponseEntity<StudentDataResponse>(sDataResponse, HttpStatus.OK);
        } catch (StudentServiceException ex) {
            StudentControllerException sControllerException = new StudentControllerException(AllMappers.STUDENT_INSERT_UNSUCCESSFULL.getMessage(), AllMappers.STUDENT_INSERT_UNSUCCESSFULL.getError(), new Date());
            return new ResponseEntity<StudentControllerException>(sControllerException, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @Operation(summary = "Delete a Single Student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Newly Created Student Data with registration Number",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentDataResponse.class))}),
            @ApiResponse(responseCode = "417", description = "Error",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentControllerException.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentControllerException.class))})
    })
    @RequestMapping(value = "/deleteStudent/{registrationNumber}", method = RequestMethod.POST)
    public ResponseEntity<?> deleteStudent(@RequestParam("registrationNumber")String registrationNumber) {
        if (registrationNumber.isEmpty()) {
            StudentControllerException sControllerException = new StudentControllerException(AllMappers.REGISTRATION_NUMBER_EMPTY.getMessage(), AllMappers.REGISTRATION_NUMBER_EMPTY.getError(), new Date());
            return new ResponseEntity<StudentControllerException>(sControllerException, HttpStatus.BAD_REQUEST);
        }
        try {
            StudentDataResponse sData = sService.deleteStudent(registrationNumber);
            return new ResponseEntity<StudentDataResponse>(sData, HttpStatus.OK);
        } catch (StudentServiceException ex) {
            StudentControllerException sControllerException = new StudentControllerException(AllMappers.STUDENT_NOT_FOUND.getMessage(), AllMappers.STUDENT_NOT_FOUND.getError(), new Date());
            return new ResponseEntity<StudentControllerException>(sControllerException, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @Operation(summary = "Update an existing Student  using registration Number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Newly Created Student Data with registration Number",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentDataResponse.class))}),
            @ApiResponse(responseCode = "417", description = "Error",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentControllerException.class))}),
    })
    @RequestMapping(value = "/updateStudent/{registrationNumber}", method = RequestMethod.POST)
    public ResponseEntity<?> updateStudent(@RequestParam("registrationNumber") String registrationNumber,@RequestBody(required = true)StudentData sData) {
        if (ObjectUtils.isEmpty(sData)) {
            StudentControllerException sControllerException = new StudentControllerException(AllMappers.STUDENT_DATA_EMPTY.getMessage(), AllMappers.STUDENT_DATA_EMPTY.getError(), new Date());
            return new ResponseEntity<StudentControllerException>(sControllerException, HttpStatus.EXPECTATION_FAILED);
        }
        try {
            StudentDataResponse sDataResponse = sService.updateStudent(registrationNumber,sData);
            return new ResponseEntity<StudentDataResponse>(sDataResponse, HttpStatus.OK);
        } catch (StudentServiceException ex) {
            StudentControllerException sControllerException = new StudentControllerException(AllMappers.STUDENT_INSERT_UNSUCCESSFULL.getMessage(), AllMappers.STUDENT_INSERT_UNSUCCESSFULL.getError(), new Date());
            return new ResponseEntity<StudentControllerException>(sControllerException, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @Operation(summary = "Get Single Student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student Details",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentDataResponse.class))}),
            @ApiResponse(responseCode = "417", description = "Error",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentControllerException.class))}),
            @ApiResponse(responseCode = "404", description = "Error",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentControllerException.class))})
    })
    @RequestMapping(value = "/getSingleStudent/{registrationNumber}", method = RequestMethod.GET)
    public ResponseEntity<?> getSingleStudent(@RequestParam("registrationNumber") String registrationNumber) {
        if (ObjectUtils.isEmpty(registrationNumber)) {
            StudentControllerException sControllerException = new StudentControllerException(AllMappers.REGISTRATION_NUMBER_EMPTY.getMessage(), AllMappers.REGISTRATION_NUMBER_EMPTY.getError(), new Date());
            return new ResponseEntity<StudentControllerException>(sControllerException, HttpStatus.EXPECTATION_FAILED);
        }
        try {
            Optional<StudentData> sDataResponse = sService.getSingleStudent(registrationNumber);
            return new ResponseEntity< Optional<StudentData>>(sDataResponse, HttpStatus.OK);
        } catch (StudentServiceException ex) {
            StudentControllerException sControllerException = new StudentControllerException(AllMappers.STUDENT_INSERT_UNSUCCESSFULL.getMessage(), AllMappers.STUDENT_INSERT_UNSUCCESSFULL.getError(), new Date());
            return new ResponseEntity<StudentControllerException>(sControllerException, HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get All Student By Section And Integer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get All Student By Section And Integer",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentDataResponse.class))}),
            @ApiResponse(responseCode = "417", description = "Error",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentControllerException.class))}),
    })
    @RequestMapping(value = "/getStudent/{section}/{standard}", method = RequestMethod.GET)
    public ResponseEntity<?> getStudentByStandardAndSection(@RequestParam("section") String section,@RequestParam("standard") Integer standard) {
        if (ObjectUtils.isEmpty(section) || ObjectUtils.isEmpty(standard)) {
            StudentControllerException sControllerException = new StudentControllerException(AllMappers.SEARCH_CRITERIA_EMPTY.getMessage(), AllMappers.SEARCH_CRITERIA_EMPTY.getError(), new Date());
            return new ResponseEntity<StudentControllerException>(sControllerException, HttpStatus.EXPECTATION_FAILED);
        }
        try {
            List<StudentData> studentList = sService.getStudentByStandardAndSection(section,standard);
            return new ResponseEntity<List<StudentData>>(studentList, HttpStatus.OK);
        } catch (StudentServiceException ex) {
            StudentControllerException sControllerException = new StudentControllerException(AllMappers.STUDENT_INSERT_UNSUCCESSFULL.getMessage(), AllMappers.STUDENT_INSERT_UNSUCCESSFULL.getError(), new Date());
            return new ResponseEntity<StudentControllerException>(sControllerException, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @Operation(summary = "Get All Student By Section And Integer and Stream")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get All Student By Section And Integer and Stream",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentDataResponse.class))}),
            @ApiResponse(responseCode = "417", description = "Error",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentControllerException.class))}),
    })
    @RequestMapping(value = "/getStudent/{section}/{standard}/{plus12Stream}", method = RequestMethod.GET)
    public ResponseEntity<?> getStudentByStandardAndSectionAndStreams(@RequestParam("section") String section, @RequestParam("standard") Integer standard, @RequestParam("plus12Stream") String plus12Stream) {
        if (ObjectUtils.isEmpty(section) ||ObjectUtils.isEmpty(standard) || ObjectUtils.isEmpty(plus12Stream)) {
            StudentControllerException sControllerException = new StudentControllerException(AllMappers.SEARCH_CRITERIA_EMPTY.getMessage(), AllMappers.SEARCH_CRITERIA_EMPTY.getError(), new Date());
            return new ResponseEntity<StudentControllerException>(sControllerException, HttpStatus.EXPECTATION_FAILED);
        }
        try {
            List<StudentData> studentList = sService.getStudentByStandardAndSectionAndStream(section,standard,plus12Stream);
            return new ResponseEntity<List<StudentData>>(studentList, HttpStatus.OK);
        } catch (StudentServiceException ex) {
            StudentControllerException sControllerException = new StudentControllerException(AllMappers.STUDENT_INSERT_UNSUCCESSFULL.getMessage(), AllMappers.STUDENT_INSERT_UNSUCCESSFULL.getError(), new Date());
            return new ResponseEntity<StudentControllerException>(sControllerException, HttpStatus.EXPECTATION_FAILED);
        }
    }

}
