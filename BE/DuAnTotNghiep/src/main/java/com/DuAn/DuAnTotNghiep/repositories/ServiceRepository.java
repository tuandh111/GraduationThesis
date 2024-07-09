package com.DuAn.DuAnTotNghiep.repositories;

import com.DuAn.DuAnTotNghiep.entities.Abnormality;
import com.DuAn.DuAnTotNghiep.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service,Integer> {
@Query("SELECT DISTINCT s " +
        "FROM DentalIssues di " +
        "JOIN IssuesTreatmentAutomation ita ON ita.dentalIssues.dentalIssuesId=di.dentalIssuesId " +
        "JOIN  Treatment t ON t.treatmentId=ita.treatment.treatmentId " +
        "JOIN ServiceTreatment st ON st.treatment.treatmentId=t.treatmentId " +
        "JOIN Service s ON st.service.serviceId=s.serviceId " +
        "WHERE di.isDeleted = false " +
        "AND di.dentalIssuesId IN :ids " +
        "ORDER BY s.serviceId DESC ")
List<Object> getServiceByDentalIssues(@Param("ids") List<Integer> ids);
}
