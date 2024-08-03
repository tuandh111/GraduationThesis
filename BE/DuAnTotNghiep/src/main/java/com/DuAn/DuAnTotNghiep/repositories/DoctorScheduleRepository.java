package com.DuAn.DuAnTotNghiep.repositories;

import com.DuAn.DuAnTotNghiep.entities.Abnormality;
import com.DuAn.DuAnTotNghiep.entities.DoctorSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DoctorScheduleRepository extends JpaRepository<DoctorSchedule,Integer> {
    @Query("SELECT ds from  DoctorSchedule ds where ds.doctor.doctorId=:id " +
            "AND ds.isDeleted=false " +
            "order by ds.date desc")
    List<DoctorSchedule> getDoctorScheduleByDoctor(@Param("id") Integer id);

    @Query("SELECT ds FROM DoctorSchedule ds WHERE " +
            "YEAR(ds.date) = YEAR(:d) AND " +
            "MONTH(ds.date) = MONTH(:d) AND " +
            "DAY(ds.date) = DAY(:d) " +
            "AND ds.isDeleted=false order by ds.date desc")
    List<DoctorSchedule> getDoctorScheduleByDate(@Param("d") Date d);
    @Query("SELECT DISTINCT (ds.doctor), ds.date FROM DoctorSchedule ds " +
            "where ds.isDeleted=false")
    List<Object> getDoctorFromDoctorSchedule();

    @Query("SELECT ds.shift,ds.doctorScheduleId  FROM DoctorSchedule ds WHERE " +
            "ds.date=:date " +
            "and ds.doctor.doctorId=:doctorId " +
            "and ds.isDeleted=false")
    List<Object> getShiftOfDoctorFromDoctorSchedule(@Param("date") Date d,@Param("doctorId") Integer doctorId);

    @Query("SELECT distinct(ds.doctor.doctorId) FROM  DoctorSchedule ds " +
            "where ds.date BETWEEN :startStr and :endStr and ds.isDeleted=false")
    List<Object> getDoctorScheduleByTimeRange(@Param("startStr") Date startDate,@Param("endStr") Date endDate);

    @Query("SELECT ds,tos FROM  DoctorSchedule ds " +
            "JOIN TimeOfShift tos ON tos.shift=ds.shift " +
            "where ds.isDeleted=false")
    List<Object> getDsAnfTos();

    @Query("SELECT ds FROM  DoctorSchedule ds " +
            "where ds.date BETWEEN :startStr and :endStr and ds.isDeleted=false")
    List<DoctorSchedule> getDSByTimeRange(@Param("startStr") Date startDate,@Param("endStr") Date endDate);

    @Query("SELECT ds FROM  DoctorSchedule ds " +
            "where ds.date BETWEEN :startStr and :endStr and ds.isDeleted=false " +
            "and ds.doctor.doctorId=:doctorId order by ds.doctorScheduleId asc")
    List<DoctorSchedule> getDSByTimeRangeAndDoctor(@Param("startStr") Date startDate,@Param("endStr") Date endDate,@Param("doctorId") Integer doctorId);


    @Query("SELECT distinct(ds.date) FROM  DoctorSchedule ds " +
            "where ds.date BETWEEN :startStr and :endStr and ds.isDeleted=false ORDER BY ds.date desc")
    List<Object> getDateDoctorScheduleInTimeRange(@Param("startStr") Date startDate,@Param("endStr") Date endDate);

}
