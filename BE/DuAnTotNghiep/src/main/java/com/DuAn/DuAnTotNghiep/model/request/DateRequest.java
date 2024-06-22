package com.DuAn.DuAnTotNghiep.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class DateRequest {
    private Date date;
}
