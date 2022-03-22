package com.studentservice.studentservice.studentcurd.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("students")
public class StudentData {

    @MongoId
    @NonNull
    @JsonProperty
    private String registrationID;
    @NonNull
    @JsonProperty
    private String firstName;
    @JsonProperty
    private String middleName;
    @NonNull
    @JsonProperty
    private String lastName;
    @NonNull
    @JsonProperty
    private int phoneNumber;
    @NonNull
    @JsonProperty
    private Parents pData;
    @JsonProperty
    private String section;
    @NonNull
    @JsonProperty
    private Integer standard;
    @NonNull
    @JsonProperty
    private Address address;
    @NonNull
    @JsonProperty
    private Date dateOfJoining;
    @NonNull
    @JsonProperty
    private Date dateOfLeaving;

}
