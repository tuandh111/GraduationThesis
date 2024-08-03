package com.DuAn.DuAnTotNghiep.repositories;

import com.DuAn.DuAnTotNghiep.entities.Abnormality;
import com.DuAn.DuAnTotNghiep.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill,Integer> {
    @Query("SELECT b FROM Bill b " +
            "WHERE (:appId IS NULL OR b.appointment.appointmentId = :appId) " +
            "AND (:pId IS NULL OR b.appointment.patient.patientId = :pId)")
    List<Bill> getByAppointmentAndPatient(@Param("appId") Integer appointmentId,@Param("pId") Integer patientId);
}
