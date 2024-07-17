package com.DuAn.DuAnTotNghiep.repositories;

import com.DuAn.DuAnTotNghiep.entities.Abnormality;
import com.DuAn.DuAnTotNghiep.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {
    @Query("SELECT DISTINCT(a.AppointmentDate) FROM Appointment a " +
            "ORDER BY a.AppointmentDate DESC")
    List<Object> getAllDateOfAppointment();

    @Query("SELECT a FROM Appointment a WHERE a.AppointmentDate=:date")
    List<Appointment> getAppointmentByDate(@Param("date") Date date);

    @Query("SELECT a from Appointment a " +
            "where a.AppointmentDate BETWEEN :startStr and :endStr")
    List<Appointment> getAllAppByTimeRange(@Param("startStr") Date startDate,@Param("endStr") Date endDate);
}
