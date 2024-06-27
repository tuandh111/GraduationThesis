package com.DuAn.DuAnTotNghiep.model.request;

import com.DuAn.DuAnTotNghiep.entities.TimeOfShift;
import lombok.Data;

import java.util.Date;

@Data
public class DoctorUnavailabilityRequest {

    private String description ;

    private Integer timeOfShiftId;

    private Date date;

    private Integer doctorId;

}
