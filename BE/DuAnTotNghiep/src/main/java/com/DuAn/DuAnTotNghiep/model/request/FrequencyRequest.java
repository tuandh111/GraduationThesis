package com.DuAn.DuAnTotNghiep.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FrequencyRequest {
    @NotNull
    private String timesOfDay;

    private String description;
}
