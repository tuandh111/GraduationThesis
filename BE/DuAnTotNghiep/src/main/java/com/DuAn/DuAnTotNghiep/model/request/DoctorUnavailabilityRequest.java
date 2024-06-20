package com.DuAn.DuAnTotNghiep.model.request;

import com.DuAn.DuAnTotNghiep.entities.TimeOfShift;
import lombok.Data;

@Data
public class DoctorUnavailabilityRequest {

    private String description ;

    private Integer timeOfShiftId;

    private Integer doctorId;

}
