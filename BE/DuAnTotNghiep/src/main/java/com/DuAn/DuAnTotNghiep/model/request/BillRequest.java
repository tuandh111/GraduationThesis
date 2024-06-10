package com.DuAn.DuAnTotNghiep.model.request;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.util.Date;
@Data
public class BillRequest {
    private String status;

    private double totalCost;

    private  String paymentMethod;

    private Date createAt;

    private int appointmentId;
}
