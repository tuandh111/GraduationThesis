package com.DuAn.DuAnTotNghiep.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {


    @NotEmpty(message = "First name is required")
    @Size(min = 2, message = "First name should have at least 2 characters")
    private String firstname;

    @NotEmpty(message = "Last name is required")
    @Size(min = 2, message = "Last name should have at least 2 characters")
    private String lastname;

    @NotEmpty(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotEmpty(message = "Password is required")
    @Size(min = 6, message = "Password should have at least 6 characters")
    private String password;

    private int roleId;
}
