package com.DuAn.DuAnTotNghiep.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class PatientAndUserRequest {

    private String fullName;

    private String phoneNumber;

    private String citizenIdentificationNumber;

    private Date birthday;

    private String imageURL;

    private String gender;

    private String address;

    @NotEmpty(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotEmpty(message = "Password is required")
    @Size(min = 8, message = "Password should have at least 8 characters")
    private String password;

}
