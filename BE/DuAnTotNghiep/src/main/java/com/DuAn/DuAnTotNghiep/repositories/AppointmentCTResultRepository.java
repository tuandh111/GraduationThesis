package com.DuAn.DuAnTotNghiep.repositories;

import com.DuAn.DuAnTotNghiep.entities.Abnormality;
import com.DuAn.DuAnTotNghiep.entities.AppointmentCTResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentCTResultRepository extends JpaRepository<AppointmentCTResult,Integer> {
    @Query("select ar from AppointmentCTResult ar " +
            "WHERE ar.appointment.appointmentId=:appId")
    List<AppointmentCTResult> getAppointmentCTResultByAppId(@Param("appId") Integer appId);
}
