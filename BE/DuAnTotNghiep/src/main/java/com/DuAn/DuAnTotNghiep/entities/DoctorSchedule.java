package com.DuAn.DuAnTotNghiep.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class DoctorSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer doctorScheduleId;

    private Date createAt;

    private boolean isDeleted = false;

    private  Date updateAt;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "doctorId")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "shiftId")
    private Shift shift;
}
