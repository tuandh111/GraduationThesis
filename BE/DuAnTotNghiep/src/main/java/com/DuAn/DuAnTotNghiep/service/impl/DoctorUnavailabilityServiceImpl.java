package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.DoctorUnavailability;
import com.DuAn.DuAnTotNghiep.model.request.DoctorUnavailabilityRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.DoctorRepository;
import com.DuAn.DuAnTotNghiep.repositories.DoctorUnavailabilityRepository;
import com.DuAn.DuAnTotNghiep.repositories.TimeOfShiftRepository;
import com.DuAn.DuAnTotNghiep.service.service.DoctorUnavailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorUnavailabilityServiceImpl implements DoctorUnavailabilityService {

    @Autowired
    DoctorUnavailabilityRepository doctorUnavailabilityRepository ;

    @Autowired
    TimeOfShiftRepository timeOfShiftRepository ;

    @Autowired
    DoctorRepository doctorRepository ;

    @Override
    public DoctorUnavailability findDoctorUnavailabilityById(int doctorUnavailabilityId) {
        return doctorUnavailabilityRepository.findById(doctorUnavailabilityId).orElseThrow(null) ;
    }

    @Override
    public List<DoctorUnavailability> findAllDoctorUnavailability() {
        return doctorUnavailabilityRepository.findAll() ;
    }

    @Override
    public List<DoctorUnavailability> findAllDoctorUnavailabilityExceptDeleted() {
        return doctorUnavailabilityRepository.findAll().stream()
                .filter(doctorUnavailability -> !doctorUnavailability.isDeleted())
                .collect(Collectors.toList()) ;
    }

    @Override
    public DoctorUnavailability saveDoctorUnavailability(DoctorUnavailabilityRequest doctorUnavailabilityRequest) {
        var doctorUnavailability = DoctorUnavailability.builder()
                .description(doctorUnavailabilityRequest.getDescription())
                .timeOfShift(timeOfShiftRepository.findById(doctorUnavailabilityRequest.getTimeOfShiftId()).orElse(null))
                .doctor(doctorRepository.findById(doctorUnavailabilityRequest.getDoctorId()).orElse(null))
                .build() ;
        doctorUnavailabilityRepository.save(doctorUnavailability) ;
        return doctorUnavailability;
    }

    @Override
    public DoctorUnavailability updateDoctorUnavailability(int doctorUnavailabilityId, DoctorUnavailabilityRequest doctorUnavailabilityRequest) {
        var doctorUnavailability = DoctorUnavailability.builder()
                .doctorUnavailabilityId(doctorUnavailabilityId)
                .description(doctorUnavailabilityRequest.getDescription())
                .timeOfShift(timeOfShiftRepository.findById(doctorUnavailabilityRequest.getTimeOfShiftId()).orElse(null))
                .doctor(doctorRepository.findById(doctorUnavailabilityRequest.getDoctorId()).orElse(null))
                .build() ;
        doctorUnavailabilityRepository.save(doctorUnavailability) ;
        return null;
    }

    @Override
    public MessageResponse delete(int doctorUnavailabilityId) {
        try {
            doctorUnavailabilityRepository.deleteById(doctorUnavailabilityId);
            return new MessageResponse("Successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageResponse("Fail");
        }
    }

    @Override
    public MessageResponse softDeleteDoctorUnavailability(int doctorUnavailabilityId) {
        try {
            var doctorUnavailability =doctorUnavailabilityRepository.findById(doctorUnavailabilityId)
                                              .orElseThrow(() -> new RuntimeException("doctor Unavailability not found"));
            doctorUnavailability.setDeleted(true);
            doctorUnavailabilityRepository.save(doctorUnavailability);
            return new MessageResponse("Successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageResponse("Fail");
        }
    }
}
