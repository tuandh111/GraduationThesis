package com.DuAn.DuAnTotNghiep.service.service;

import com.DuAn.DuAnTotNghiep.entities.Shift;
import com.DuAn.DuAnTotNghiep.entities.Specialty;
import com.DuAn.DuAnTotNghiep.model.request.ShiftRequest;
import com.DuAn.DuAnTotNghiep.model.request.SpecialtyRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;

import java.util.List;

public interface SpecialtyService {
    Specialty findBySpecialtyId(int SpecialtyId);

    List<Specialty> findAllSpecialty();

    List<Specialty> findAllSpecialtyExceptDeleted();

    Specialty saveSpecialty(SpecialtyRequest specialtyRequest);

    Specialty updateSpecialty(int specialtyId, SpecialtyRequest specialtyRequest);

    MessageResponse delete(int specialtyId);

    MessageResponse softDeleteSpecialty(int specialtyId);
}
