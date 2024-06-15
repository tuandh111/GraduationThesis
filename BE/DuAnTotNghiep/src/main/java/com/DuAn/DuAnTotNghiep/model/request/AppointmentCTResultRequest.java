package com.DuAn.DuAnTotNghiep.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class AppointmentCTResultRequest {

    private String image;

    private int dentalStaffId;

    private int AppointmentId;

    private Date date;

    private int imagingPlanesId;
}
