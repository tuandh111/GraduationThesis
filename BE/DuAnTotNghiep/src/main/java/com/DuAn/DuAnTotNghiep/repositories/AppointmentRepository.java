package com.DuAn.DuAnTotNghiep.repositories;

import com.DuAn.DuAnTotNghiep.entities.Abnormality;
import com.DuAn.DuAnTotNghiep.entities.Appointment;
import com.DuAn.DuAnTotNghiep.model.request.InvoiceRequest;
import com.DuAn.DuAnTotNghiep.model.response.InvoiceRes;
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

    @Query("SELECT a from Appointment a " +
            "where a.patient.patientId=:patientId and a.AppointmentDate = :date and a.isDeleted=false")
    List<Appointment> getAllAppByPatient(@Param("date") Date now,@Param("patientId") Integer patientId);


    @Query("SELECT a FROM Appointment a WHERE " +
            "(:date is null or DAY(a.AppointmentDate)=:date) " +
            "AND (:month is null or MONTH(a.AppointmentDate)=:month) " +
            "AND (:year is null or YEAR(a.AppointmentDate)=:year)")
    List<Appointment> getAppointmentsByDateMonthYear(@Param("date") Integer date ,@Param("month") Integer month,
                                                @Param("year") Integer year);

    @Query("Select a FROM Appointment a " +
            "WHERE (:appStatus is null or a.appointmentStatus.appointment_StatusId=:appStatus) " +
            "AND a.appointmentId not in (select b.appointments.appointmentId from Bill b WHERE b.isDeleted=false)")
    List<Appointment> getAppointmentWithOutBill(@Param("appStatus") Integer appStatus);

}
