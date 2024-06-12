package com.DuAn.DuAnTotNghiep.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class DoctorScheduleRequest {
    @NotNull
    private Date createAt;
    @NotNull
    private Date updateAt;

    @NotNull
    private Date date;

    @NotNull
    private Integer shiftId;

    @NotNull
    private Integer doctorId;
}
