package com.DuAn.DuAnTotNghiep.model.request;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.util.Date;

@Data
@Builder
public class PatientAndUserRequest {


    @NotEmpty(message = "Full name is required")
    @Size(max = 100, message = "Full name should not exceed 100 characters")
    @Pattern(regexp = "^[\\p{L}\\s'-]+$", message = "Full name should only contain letters, spaces, hyphens, or apostrophes")
    private String fullName;

    @NotEmpty(message = "Phone number is required")
    @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$", message = "Phone number should be valid and contain 10 digits")
    private String phoneNumber;

    @NotEmpty(message = "Citizen Identification Number is required")
    @Size(min = 9, max = 12, message = "Citizen Identification Number should be between 9 and 12 characters")
    private String citizenIdentificationNumber;

    @NotNull(message = "Birthday is required")
    @Past(message = "Birthday must be a past date")
    private Date birthday;

    @NotEmpty(message = "Image URL is required")
    @URL(message = "Image URL should be valid")
    private String imageURL;

    @NotEmpty(message = "Gender is required")
    @Pattern(regexp = " MALE| FEMALE|UNISEX", message = "Gender should be Male, Female, or Other")
    private String gender;

    @NotEmpty(message = "Address is required")
    @Size(max = 255, message = "Address should not exceed 255 characters")
    private String address;

    @NotEmpty(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotEmpty(message = "Password is required")
    @Size(min = 8, message = "Password should have at least 8 characters")
    private String password;

}
