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
            "AND ds.isDeleted=false")
    List<DoctorSchedule> getDoctorScheduleByDate(@Param("d") Date d);

}
