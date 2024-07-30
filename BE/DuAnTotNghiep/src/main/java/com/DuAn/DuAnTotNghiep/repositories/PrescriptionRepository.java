package com.DuAn.DuAnTotNghiep.repositories;

import com.DuAn.DuAnTotNghiep.entities.Abnormality;
import com.DuAn.DuAnTotNghiep.entities.Prescription;
import com.DuAn.DuAnTotNghiep.entities.PrescriptionMedicines;
import com.DuAn.DuAnTotNghiep.model.response.PrescriptionWithMedicinesResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription,Integer> {

    @Query("SELECT pr FROM Prescription pr " +
            "JOIN PrescriptionMedicines pm ON pm.prescription.prescriptionId=pr.prescriptionId " +
            "JOIN Medicines me ON me.medicinesId=pm.medicines.medicinesId " +
            "JOIN AppointmentPatientRecord apr ON apr.appointmentPatientRecordId=pr.appointmentPatientRecord.appointmentPatientRecordId " +
            "JOIN Appointment ap ON ap.appointmentPatientRecord.appointmentPatientRecordId=apr.appointmentPatientRecordId " +
            "WHERE (:appId IS NULL OR ap.appointmentId = :appId)")
    List<Prescription> getPrescriptions(@Param("appId") Integer appId);


    @Query("SELECT pm FROM PrescriptionMedicines pm " +
            "JOIN Prescription pr ON pr.prescriptionId=pm.prescription.prescriptionId " +
            "JOIN Medicines me ON me.medicinesId=pm.medicines.medicinesId " +
            "WHERE pr.prescriptionId IN :prescriptionIds")
    List<PrescriptionMedicines> getMedicinesByPrescriptionIds(@Param("prescriptionIds") List<Integer> prescriptionIds);
}
