package com.DuAn.DuAnTotNghiep.model.request;

import com.DuAn.DuAnTotNghiep.entities._enum.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class PatientRequest {
    @NotNull
    private  String fullName;
    @NotNull
    private String  phoneNumber;
    @NotNull
    private String Type;
    @NotNull
    private String CitizenIdentificationNumber;
    @NotNull
    private Date birthday;
    @NotNull
    private String imageURL;
    @NotNull
    private String gender;
}
