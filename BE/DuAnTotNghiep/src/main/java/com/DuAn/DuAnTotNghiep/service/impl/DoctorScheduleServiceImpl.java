package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.DoctorSchedule;
import com.DuAn.DuAnTotNghiep.entities.Role;
import com.DuAn.DuAnTotNghiep.entities.Shift;
import com.DuAn.DuAnTotNghiep.model.request.DoctorScheduleRequest;
import com.DuAn.DuAnTotNghiep.model.request.RoleRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.DoctorRepository;
import com.DuAn.DuAnTotNghiep.repositories.DoctorScheduleRepository;
import com.DuAn.DuAnTotNghiep.repositories.ShiftRepository;
import com.DuAn.DuAnTotNghiep.repositories.TimeOfShiftRepository;
import com.DuAn.DuAnTotNghiep.service.service.DoctorScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorScheduleServiceImpl implements DoctorScheduleService {
    @Autowired
    DoctorScheduleRepository doctorScheduleRepository;
    @Autowired
    TimeOfShiftRepository timeOfShiftRepository;
    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public DoctorSchedule findByDoctorScheduleId(int doctorScheduleId) {
        return doctorScheduleRepository.findById(doctorScheduleId).orElseThrow(null) ;
    }

    @Override
    public List<DoctorSchedule> findAll() {
        return doctorScheduleRepository.findAll().stream()
                       .filter(doctorSchedule -> !doctorSchedule.isDeleted())
                       .collect(Collectors.toList());
    }

    @Override
    public DoctorSchedule saveDoctorSchedule(DoctorScheduleRequest doctorScheduleRequest) {
        var doctorSchedule = DoctorSchedule
                                     .builder()
                                     .doctor(doctorRepository.findById(doctorScheduleRequest.getDoctorId()).orElse(null))
                                     .createAt(doctorScheduleRequest.getCreateAt())
                                     .updateAt(doctorScheduleRequest.getUpdateAt())
                                     .date(doctorScheduleRequest.getDate())
                                     .timeOfShift(timeOfShiftRepository.findById(doctorScheduleRequest.getTimeOfShiftId()).orElse(null))
                                     .build();
        doctorScheduleRepository.save(doctorSchedule);
        return doctorSchedule;
    }

    @Override
    public DoctorSchedule updateDoctorSchedule(int doctorScheduleId, DoctorScheduleRequest doctorScheduleRequest) {
        var doctorSchedule = DoctorSchedule
                                     .builder()
                                     .doctorScheduleId(doctorScheduleId)
                                     .doctor(doctorRepository.findById(doctorScheduleRequest.getDoctorId()).orElseThrow(null))
                                     .createAt(doctorScheduleRequest.getCreateAt())
                                     .updateAt(doctorScheduleRequest.getUpdateAt())
                                     .date(doctorScheduleRequest.getDate())
                                     .timeOfShift(timeOfShiftRepository.findById(doctorScheduleRequest.getTimeOfShiftId()).orElse(null))
                                     .build();
        doctorScheduleRepository.save(doctorSchedule);
        return doctorSchedule;
    }

    @Override
    public MessageResponse delete(int doctorScheduleId) {
        try {
            doctorScheduleRepository.deleteById(doctorScheduleId);
            return new MessageResponse("successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageResponse("fail");
        }

    }

    @Override
    public MessageResponse sortDeleteDoctorSchedule(int doctorScheduleId) {
        try {
            var doctorSchedule = DoctorSchedule
                                         .builder()
                                         .doctorScheduleId(doctorScheduleId)
                                         .isDeleted(true)
                                         .build();
            doctorScheduleRepository.save(doctorSchedule);
            return new MessageResponse("successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageResponse("fail");
        }
    }
}
