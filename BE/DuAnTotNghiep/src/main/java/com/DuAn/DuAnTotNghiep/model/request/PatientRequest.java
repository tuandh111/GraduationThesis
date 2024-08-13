package com.DuAn.DuAnTotNghiep.model.request;

import com.DuAn.DuAnTotNghiep.entities._enum.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class PatientRequest {

    private String fullName;

    private String  phoneNumber;

    private String citizenIdentificationNumber;

    private Date birthday;

    private String imageURL;

    private String gender;

    private String address;

}
