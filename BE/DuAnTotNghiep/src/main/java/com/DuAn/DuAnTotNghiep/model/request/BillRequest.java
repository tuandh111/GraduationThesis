package com.DuAn.DuAnTotNghiep.model.request;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BillRequest {
    private String status;

    private double totalCost;

    private String paymentMethod;

    private Date createAt;

    private int appointmentId;
}
