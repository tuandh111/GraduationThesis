package com.DuAn.DuAnTotNghiep.repositories;

import com.DuAn.DuAnTotNghiep.entities.Abnormality;
import com.DuAn.DuAnTotNghiep.entities.AppointmentService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentServiceRepository extends JpaRepository<AppointmentService,Integer> {
    @Query("SELECT as2 FROM AppointmentService as2 " +
            "WHERE as2.appointment.appointmentId=:appId")
    List<AppointmentService> getAppointmentServiceByAppId(@Param("appId") Integer appointmentId);
}
