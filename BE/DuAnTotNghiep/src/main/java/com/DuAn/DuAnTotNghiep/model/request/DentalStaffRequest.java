package com.DuAn.DuAnTotNghiep.model.request;

import com.DuAn.DuAnTotNghiep.entities._enum.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DentalStaffRequest {
    @NotNull
    private String fullName;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String address;
    @NotNull
    private String birthday;
    @NotNull
    private String imageURL;
    @NotNull
    private String gender;
    @NotNull
    private int departmentId;
}
