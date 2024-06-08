package com.DuAn.DuAnTotNghiep.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class AppointmentCTResultRequest {
    @NotNull
    private String image;
    @NotNull
    private int dentalStaffId;
    @NotNull
    private int AppointmentId;
    @NotNull
    private Date date;
    @NotNull
    private int imagingPlanesId;
}
