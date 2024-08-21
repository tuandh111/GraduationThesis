package com.DuAn.DuAnTotNghiep.repositories;

import com.DuAn.DuAnTotNghiep.entities.Abnormality;
import com.DuAn.DuAnTotNghiep.entities.DentalSuppliesAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DentalSuppliesAppointmentRepository extends JpaRepository<DentalSuppliesAppointment,Integer> {
    @Query("select dsa from DentalSuppliesAppointment dsa " +
            "where (:appId is null or dsa.appointment.appointmentId=:appId) and dsa.isDeleted=false")
    List<DentalSuppliesAppointment> getByAppointmentId(@Param("appId") Integer appId);
}
