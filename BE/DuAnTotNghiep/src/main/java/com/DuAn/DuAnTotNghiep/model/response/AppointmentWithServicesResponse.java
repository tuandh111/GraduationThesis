package com.DuAn.DuAnTotNghiep.model.response;


import com.DuAn.DuAnTotNghiep.entities.Appointment;
import com.DuAn.DuAnTotNghiep.entities.Service;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentWithServicesResponse {
    private Appointment appointment;
    private List<Service> services;

    private boolean status;


}
