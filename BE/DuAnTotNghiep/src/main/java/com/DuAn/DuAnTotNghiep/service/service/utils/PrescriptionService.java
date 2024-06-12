package com.DuAn.DuAnTotNghiep.service.service.utils;

import com.DuAn.DuAnTotNghiep.entities.Prescription;
import com.DuAn.DuAnTotNghiep.model.request.PrescriptionRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;

import java.util.List;

public interface PrescriptionService {
    Prescription findByPrescriptionId(int prescriptionId) ;

    List<Prescription> findAllPrescriptions() ;

    Prescription savePrescription(PrescriptionRequest prescriptionRequest) ;

    Prescription updatePrescription(int prescriptionId, PrescriptionRequest prescriptionRequest) ;

    MessageResponse deletePrescription(int prescriptionId) ;

    MessageResponse softDeletePrescription(int prescriptionId) ;
}
