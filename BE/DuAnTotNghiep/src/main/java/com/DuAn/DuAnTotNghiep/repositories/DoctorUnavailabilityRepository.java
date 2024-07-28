package com.DuAn.DuAnTotNghiep.repositories;

import com.DuAn.DuAnTotNghiep.entities.AppointmentService;
import com.DuAn.DuAnTotNghiep.entities.DoctorSchedule;
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

    @Query("SELECT du FROM DoctorUnavailability du " +
            "WHERE du.appointment.appointmentId=:appId")
    List<DoctorUnavailability> getDoctorUnavailabilityByAppId(@Param("appId") Integer appointmentId);

    @Query("SELECT distinct(du.appointment.AppointmentDate) FROM DoctorUnavailability du " +
            "WHERE (:startStr IS NULL OR du.appointment.AppointmentDate >= :startStr) " +
            "AND (:endStr IS NULL OR du.appointment.AppointmentDate <= :endStr) " +
            "AND (:doctorId IS NULL OR du.appointment.doctor.doctorId = :doctorId) " +
            "order by du.appointment.AppointmentDate desc ")
    List<Object> getDistinctDateOfDuByTimeRange(@Param("startStr") Date startDate,@Param("endStr") Date endDate,@Param("doctorId") Integer doctorId);


    @Query("SELECT du FROM DoctorUnavailability du WHERE " +
            "YEAR(du.appointment.AppointmentDate) = YEAR(:d) AND " +
            "MONTH(du.appointment.AppointmentDate) = MONTH(:d) AND " +
            "DAY(du.appointment.AppointmentDate) = DAY(:d) " +
            "order by du.appointment.AppointmentDate desc")
    List<DoctorUnavailability> getDoctorUnavailabilityByDate(@Param("d") Date d);
}
