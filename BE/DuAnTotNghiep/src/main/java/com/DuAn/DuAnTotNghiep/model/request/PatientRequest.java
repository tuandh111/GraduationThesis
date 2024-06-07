package com.DuAn.DuAnTotNghiep.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PatientRequest {
    @NotNull
    private  String distribution;
    @NotNull
    private String  name;
    @NotNull
    private String address;
    @NotNull
    private  String contactPerson;
    @NotNull
    private String email;
    @NotNull
    private String  note;
    @NotNull
    private String taxCode;
}
