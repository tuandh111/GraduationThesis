package com.DuAn.DuAnTotNghiep.repositories;

import com.DuAn.DuAnTotNghiep.entities.Abnormality;
import com.DuAn.DuAnTotNghiep.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill,Integer> {
    @Query("SELECT b FROM Bill b " +
            "WHERE (:appId IS NULL OR b.appointments.appointmentId = :appId) " +
            "AND (:pId IS NULL OR b.appointments.patient.patientId = :pId)")
    List<Bill> getByAppointmentAndPatient(@Param("appId") Integer appointmentId,@Param("pId") Integer patientId);

    @Query("select SUM(b.totalCost) from Bill b " +
            "WHERE (:date is null or b.createAt = :date) " +
            "AND (:month is null or MONTH(b.createAt)=:month) " +
            "AND (:year is null or YEAR (b.createAt)=:year)"
    )
    Double getRevenue(@Param("date") Date date,@Param("month") Integer month,@Param("year") Integer year);

    @Query("SELECT as2.service,(as2.price*as2.quantity) FROM AppointmentService as2 " +
            "JOIN Service s ON s.serviceId=as2.service.serviceId " +
            "JOIN Appointment a ON a.appointmentId=as2.appointment.appointmentId " +
            "JOIN Bill b ON b.appointments.appointmentId=a.appointmentId " +
            "WHERE (:day is null or day(b.createAt) =:day) " +
            "AND (:month is null or MONTH(b.createAt)=:month) " +
            "AND (:year is null or year(b.createAt)=:year) " +
            "GROUP BY as2.service, (as2.price*as2.quantity)" +
            "ORDER BY (as2.price*as2.quantity) DESC " +
            "LIMIT 5")
    List<Object[]> getTop5Service(@Param("day") Integer day,@Param("month") Integer month,@Param("year") Integer year);
}
