package com.DuAn.DuAnTotNghiep.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shiftId;

    private String name;

    private boolean isDeleted = false;

    private String description;

    private LocalTime beginTime;

    private LocalTime endTime;

    @OneToMany(mappedBy = "timeOfShiftId")
    @JsonIgnore
    private List<TimeOfShift> timeOfShifts;

    @OneToMany(mappedBy = "doctorScheduleId")
    @JsonIgnore
    private List<DoctorSchedule> doctorSchedules;
}
