package com.DuAn.DuAnTotNghiep.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SpecialtyRequest {


    private String specialtyName;

    private String description;
}
