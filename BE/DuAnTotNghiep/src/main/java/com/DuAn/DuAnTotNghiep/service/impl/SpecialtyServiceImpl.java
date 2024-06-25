package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.Specialty;
import com.DuAn.DuAnTotNghiep.model.request.SpecialtyRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.SpecialtyRepository;
import com.DuAn.DuAnTotNghiep.service.service.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpecialtyServiceImpl implements SpecialtyService {
    @Autowired
    SpecialtyRepository specialtyRepository;

    @Override
    public Specialty findBySpecialtyId(int SpecialtyId) {
        return specialtyRepository.findById(SpecialtyId).orElseThrow(null);
    }

    @Override
    public List<Specialty> findAllSpecialty() {
        return specialtyRepository.findAll() ;
    }

    @Override
    public List<Specialty> findAllSpecialtyExceptDeleted() {
        return specialtyRepository.findAll().stream()
                .filter(specialty -> !specialty.isDeleted())
                .collect(Collectors.toList());
    }

    @Override
    public Specialty saveSpecialty(SpecialtyRequest specialtyRequest) {
        var specialty= Specialty
                               .builder()
                               .specialtyName(specialtyRequest.getSpecialtyName())
                               .description(specialtyRequest.getDescription())
                               .build();
        specialtyRepository.save(specialty);
        return specialty;
    }

    @Override
    public Specialty updateSpecialty(int specialtyId, SpecialtyRequest specialtyRequest) {
        var specialty= Specialty
                               .builder()
                               .specialtyId(specialtyId)
                               .specialtyName(specialtyRequest.getSpecialtyName())
                               .description(specialtyRequest.getDescription())
                               .build();
        specialtyRepository.save(specialty);
        return specialty;
    }

    @Override
    public MessageResponse delete(int specialtyId) {
        try {
            specialtyRepository.deleteById(specialtyId);
            return new MessageResponse("successfully");
        }catch (Exception e) {
            e.printStackTrace();
            return new MessageResponse("fail");
        }
    }

    @Override
    public MessageResponse softDeleteSpecialty(int specialtyId) {
        try {
            var specialty=  specialtyRepository.findById(specialtyId)
                                    .orElseThrow(() -> new RuntimeException("specialty not found"));
            specialty.setDeleted(true) ;
            specialtyRepository.save(specialty);
            return new MessageResponse("successfully");
        }catch (Exception e) {
            e.printStackTrace();
            return new MessageResponse("fail");
        }
    }
}
