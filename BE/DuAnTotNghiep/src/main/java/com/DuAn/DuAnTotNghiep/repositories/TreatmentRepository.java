package com.DuAn.DuAnTotNghiep.repositories;

import com.DuAn.DuAnTotNghiep.entities.Abnormality;
import com.DuAn.DuAnTotNghiep.entities.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment,Integer> {

    @Query("SELECT DISTINCT t " +
            "FROM Treatment t " +
            "JOIN IssuesTreatmentAutomation ita ON ita.treatment.treatmentId=t.treatmentId " +
            "JOIN  DentalIssues di ON di.dentalIssuesId=ita.dentalIssues.dentalIssuesId " +
            "WHERE t.isDeleted = false " +
            "AND di.dentalIssuesId IN :ids " +
            "ORDER BY t.treatmentId DESC ")
    List<Object> getTreatmentByDentalIssues(@Param("ids") List<Integer> ids);


    @Query("SELECT DISTINCT s " +
            "FROM Service s " +
            "JOIN ServiceTreatment st ON st.service.serviceId=s.serviceId " +
            "JOIN  Treatment t ON t.treatmentId=st.treatment.treatmentId " +
            "WHERE s.isDeleted = false " +
            "AND t.treatmentId IN :ids " +
            "ORDER BY s.serviceId DESC ")
    List<Object> getServiceByTreatment(@Param("ids") List<Integer> ids);
}
