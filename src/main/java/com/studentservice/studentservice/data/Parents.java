package com.studentservice.studentservice.data;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Parents {

    @NonNull
    private String fatherFirstname;
    private String fatherMiddleName;
    @NonNull
    private String fatherLastName;
    @NonNull
    private String motherFirstName;
    @NonNull
    private String motherLastName;
    @NonNull
    private Address address;
    private int fatherPhoneNumber;
    private int motherPhoneNumber;
}
