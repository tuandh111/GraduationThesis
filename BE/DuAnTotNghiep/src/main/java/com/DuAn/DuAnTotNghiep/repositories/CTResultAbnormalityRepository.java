package com.DuAn.DuAnTotNghiep.repositories;

import com.DuAn.DuAnTotNghiep.entities.Abnormality;
import com.DuAn.DuAnTotNghiep.entities.CTResultAbnormality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CTResultAbnormalityRepository extends JpaRepository<CTResultAbnormality,Integer> {
    @Query("select ca from CTResultAbnormality ca " +
            "JOIN AppointmentCTResult ac ON ac.appointmentCTResultId=ca.appointmentCTResult.appointmentCTResultId " +
            "WHERE (:appId is null or ac.appointment.appointmentId=:appId)")
    List<CTResultAbnormality> getCTResultAbnormalityByAppointmentId(@Param("appId") Integer appId);
}
