package com.DuAn.DuAnTotNghiep.model.request;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.util.Date;
@Data
public class ServiceRequest {

    private String serviceName;

    private double price;

    private int timeEstimate;

    private int serviceTypeId;

    private String description;

}
