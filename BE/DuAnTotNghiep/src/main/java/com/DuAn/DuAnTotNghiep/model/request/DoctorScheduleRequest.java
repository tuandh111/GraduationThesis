package com.DuAn.DuAnTotNghiep.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class DoctorScheduleRequest {

    private Date createAt;

    private Date updateAt;


    private Date date;

    private Integer shiftId;

    private Integer doctorId;
}
