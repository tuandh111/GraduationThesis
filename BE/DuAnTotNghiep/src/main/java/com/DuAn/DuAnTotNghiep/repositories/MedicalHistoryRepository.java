package com.DuAn.DuAnTotNghiep.repositories;

import com.DuAn.DuAnTotNghiep.entities.Abnormality;
import com.DuAn.DuAnTotNghiep.entities.MedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory,Integer> {

    @Query("SELECT mh from MedicalHistory mh " +
            "JOIN  MedicalHistoryDetail md ON md.medicalHistory.medicalHistoryId=mh.medicalHistoryId " +
            "WHERE (:patientId is null or md.patient.patientId=:patientId)")
    List<MedicalHistory> getMedicalHistoryByPatient(@Param("patientId") Integer patientId);
}
