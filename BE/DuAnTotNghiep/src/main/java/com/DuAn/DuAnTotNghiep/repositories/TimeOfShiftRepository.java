package com.DuAn.DuAnTotNghiep.repositories;

import com.DuAn.DuAnTotNghiep.entities.TimeOfShift;
import jakarta.websocket.server.PathParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TimeOfShiftRepository extends JpaRepository<TimeOfShift,Integer> {
    @Query("select tos from TimeOfShift tos where tos.shift.shiftId=:id")
    List<TimeOfShift> getTimeOfShiftByShift(@Param("id") Integer id);
    @Query("SELECT ds, tos " +
            "FROM DoctorSchedule ds " +
            "JOIN TimeOfShift tos ON tos.shift.shiftId = ds.shift.shiftId " +
            "WHERE ds.doctor.doctorId = :doctorId " +
            "AND ds.date = :d " +
            "AND ds.shift.shiftId = :shiftId " +
            "AND ds.isDeleted=false " +
            "AND tos.timeOfShiftId NOT IN (SELECT du.timeOfShift.timeOfShiftId FROM DoctorUnavailability du where du.date=:d " +
            "and du.isDeleted=false and du.appointment.doctor.doctorId=:doctorId)")
    List<Object> getTimeOfShiftAvailable(@Param("doctorId") Integer doctorId, @Param("d") Date d, @Param("shiftId") Integer shiftId);

    @Query("SELECT ds, tos " +
            "FROM DoctorSchedule ds " +
            "JOIN TimeOfShift tos ON tos.shift.shiftId = ds.shift.shiftId " +
            "WHERE ds.doctor.doctorId = :doctorId " +
            "AND FUNCTION('MONTH', ds.date) = :month " +
            "AND FUNCTION('YEAR', ds.date) = :year " +
            "AND tos.timeOfShiftId NOT IN (SELECT du.timeOfShift.timeOfShiftId FROM DoctorUnavailability du)")
    List<Object> getTimeOfShiftAvailableByMonth(@Param("doctorId") Integer doctorId, @Param("month") int month, @Param("year") int year);

    @Query("SELECT ds, tos " +
            "FROM DoctorSchedule ds " +
            "JOIN TimeOfShift tos ON tos.shift.shiftId = ds.shift.shiftId " +
            "WHERE ds.doctor.doctorId = :doctorId " +
            "AND ds.date = :d " +
            "AND ds.shift.shiftId = :shiftId " +
            "AND ds.isDeleted=false")
    List<Object> getAllTimeOfShiftDetails(@Param("doctorId") Integer doctorId, @Param("d") Date d, @Param("shiftId") Integer shiftId);


}
