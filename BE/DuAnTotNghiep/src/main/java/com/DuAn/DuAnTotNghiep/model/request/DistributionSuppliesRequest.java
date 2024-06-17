package com.DuAn.DuAnTotNghiep.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DistributionSuppliesRequest {

    private  String distribution;

    private String  name;

    private String address;

    private  String contactPerson;

    private String email;

    private String  note;

    private String taxCode;
}
