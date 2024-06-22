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
    @Query("SELECT ds from  DoctorSchedule ds where ds.doctor.doctorId=:id")
    List<DoctorSchedule> getDoctorScheduleByDoctor(@Param("id") Integer id);

    @Query("SELECT ds from  DoctorSchedule ds where ds.date=:d")
    List<DoctorSchedule> getDoctorScheduleByDate(@Param("d") Date d);

}
