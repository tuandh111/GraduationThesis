package com.DuAn.DuAnTotNghiep.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DateRangeRequest {
    private String startDate;
    private String endDate;

}
