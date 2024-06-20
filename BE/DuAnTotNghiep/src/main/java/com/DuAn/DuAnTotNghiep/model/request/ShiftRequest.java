package com.DuAn.DuAnTotNghiep.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalTime;

@Data
public class ShiftRequest {

    private String shiftName;

    private String description;

    private LocalTime beginTime;

    private LocalTime endTime;
}
