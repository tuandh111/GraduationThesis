package com.DuAn.DuAnTotNghiep.service.service;

import com.DuAn.DuAnTotNghiep.entities.Prescription;
import com.DuAn.DuAnTotNghiep.model.request.PrescriptionRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.model.response.PrescriptionWithMedicinesResponse;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PrescriptionService {
    Prescription findByPrescriptionId(int prescriptionId) ;

    List<Prescription> findAllPrescriptions() ;

    List<Prescription> findAllPrescriptionsExceptDeleted() ;

    Prescription savePrescription(PrescriptionRequest prescriptionRequest) ;

    Prescription updatePrescription(int prescriptionId, PrescriptionRequest prescriptionRequest) ;

    MessageResponse deletePrescription(int prescriptionId) ;

    MessageResponse softDeletePrescription(int prescriptionId) ;

    List<PrescriptionWithMedicinesResponse> findPrescriptionByAppointment(Integer appId);
}
