package com.DuAn.DuAnTotNghiep.model.request;

import lombok.Data;

@Data
public class DistributionMedicinesRequest {
    private String distributionName;
    private String name;
    private String address;
    private String email;
    private String contactPerson;
    private String note;
    private String taxCode;
}
