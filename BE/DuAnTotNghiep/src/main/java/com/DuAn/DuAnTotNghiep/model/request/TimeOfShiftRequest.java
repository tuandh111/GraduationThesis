package com.DuAn.DuAnTotNghiep.model.request;

import com.DuAn.DuAnTotNghiep.entities.DoctorSchedule;
import com.DuAn.DuAnTotNghiep.entities.Shift;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
public class TimeOfShiftRequest {


    private LocalTime beginTime;

    private LocalTime endTime;

    private Integer shiftId;

}
