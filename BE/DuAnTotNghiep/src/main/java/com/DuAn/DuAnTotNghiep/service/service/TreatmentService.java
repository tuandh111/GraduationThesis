package com.DuAn.DuAnTotNghiep.service.service;

import com.DuAn.DuAnTotNghiep.entities.Shift;
import com.DuAn.DuAnTotNghiep.entities.Treatment;
import com.DuAn.DuAnTotNghiep.model.request.ShiftRequest;
import com.DuAn.DuAnTotNghiep.model.request.TreatmentRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TreatmentService {
    Treatment findByTreatmentId(int treatmentId);

    List<Treatment> findAllTreatment();

    List<Treatment> findAllTreatmentExceptDeleted();

    Treatment saveTreatment(TreatmentRequest treatmentRequest);

    Treatment updateTreatment(int treatmentId, TreatmentRequest treatmentRequest);

    MessageResponse delete(int treatmentId);

    MessageResponse softDeleteTreatment(int treatmentId);

    List<Object> findTreatmentByDentalIssues(List<Integer> ids);

    List<Object> findServiceByTreatment(List<Integer> ids);
}
