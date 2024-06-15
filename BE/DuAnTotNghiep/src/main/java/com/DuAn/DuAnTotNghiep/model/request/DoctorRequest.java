package com.DuAn.DuAnTotNghiep.model.request;

import com.DuAn.DuAnTotNghiep.entities.Specialty;
import com.DuAn.DuAnTotNghiep.entities._enum.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DoctorRequest {

    private String degrees;

    private String signature;

    private String fullName;

    private String phoneNumber;

    private String address;

    private String birthday;

    private String image;

    private String gender;

    private int specialtyId;
}
