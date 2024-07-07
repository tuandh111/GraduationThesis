package com.DuAn.DuAnTotNghiep.repositories;

import com.DuAn.DuAnTotNghiep.entities.DoctorUnavailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DoctorUnavailabilityRepository extends JpaRepository<DoctorUnavailability,Integer> {
    @Query("select du from DoctorUnavailability du " +
            "Join du.appointment a " +
            "where a.doctor.doctorId=:doctorId and du.isDeleted=false ")
    List<DoctorUnavailability> getDoctorUnavailabilityByDoctor(@Param("doctorId") Integer doctorId);

    @Query("SELECT du.timeOfShift.shift,du.doctorUnavailabilityId  FROM DoctorUnavailability du WHERE " +
            "du.date=:date " +
            "and du.appointment.doctor.doctorId=:doctorId " +
            "and du.isDeleted=false")
    List<Object> getShiftOfDoctorFromDoctorUnavailability(@Param("date") Date d, @Param("doctorId") Integer doctorId);
}
