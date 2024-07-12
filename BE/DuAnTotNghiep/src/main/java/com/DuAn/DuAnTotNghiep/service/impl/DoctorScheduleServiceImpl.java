package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.AppointmentStatus;
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
import com.DuAn.DuAnTotNghiep.service.service.AppointmentStatusService;
import com.DuAn.DuAnTotNghiep.service.service.DoctorScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DoctorScheduleServiceImpl implements DoctorScheduleService {
    @Autowired
    DoctorScheduleRepository doctorScheduleRepository;
    @Autowired
    ShiftRepository shiftRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    AppointmentStatusService appointmentStatusService;

    @Override
    public DoctorSchedule findByDoctorScheduleId(int doctorScheduleId) {
        return doctorScheduleRepository.findById(doctorScheduleId).orElseThrow(null) ;
    }

    @Override
    public List<DoctorSchedule> findAllDoctorScheduleByDoctor(int doctorId) {
        return doctorScheduleRepository.getDoctorScheduleByDoctor(doctorId);
    }

    @Override
    public List<DoctorSchedule> findAllDoctorScheduleByDate(Date date) {
        return doctorScheduleRepository.getDoctorScheduleByDate(date);
    }

    @Override
    public List<Object> findDoctorFromDoctorSchedule() {
        return doctorScheduleRepository.getDoctorFromDoctorSchedule();
    }

    @Override
    public List<Object> findShiftOfDoctorFromDoctorSchedule(Date date, int doctorId) {
        return doctorScheduleRepository.getShiftOfDoctorFromDoctorSchedule(date,doctorId);
    }

    @Override
    public List<Object> findDoctorScheduleByTimeRange(Date startDate, Date endDate) {
        return doctorScheduleRepository.getDoctorScheduleByTimeRange(startDate,endDate);
    }

    @Override
    public List<DoctorSchedule> findAllDoctorSchedule() {
        return doctorScheduleRepository.findAll() ;
    }

    @Override
    public List<DoctorSchedule> findAllDoctorScheduleExceptDeleted() {
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
                                     .shift(shiftRepository.findById(doctorScheduleRequest.getShiftId()).orElse(null))
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
                                     .shift(shiftRepository.findById(doctorScheduleRequest.getShiftId()).orElse(null))
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
    public MessageResponse softDeleteDoctorSchedule(int doctorScheduleId) {
        try {
            var doctorSchedule = doctorScheduleRepository.findById(doctorScheduleId)
                                         .orElseThrow(() -> new RuntimeException("doctor Schedule not found"));
            doctorSchedule.setDeleted(true);
            doctorScheduleRepository.save(doctorSchedule);
            return new MessageResponse("successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageResponse("fail");
        }
    }

    @Override
    public Map<String, List<AppointmentStatus>> findDSWithAppointmentStatus(Date date) {
        Map<String,List<AppointmentStatus>> dsStatusMap = new HashMap<>();

        List<DoctorSchedule> listDs = this.findAllDoctorScheduleByDate(date);
        List<AppointmentStatus> appointmentStatuses = appointmentStatusService.findAllAppointmentStatusExceptDeleted();
        for (DoctorSchedule ds:listDs){
            dsStatusMap.put(ds.getDoctor().getDoctorId()+"-"+ds.getDoctor().getFullName(),appointmentStatuses);
        }
        return dsStatusMap;
    }

    @Override
    public List<Object> findDsAnfTos() {
        return doctorScheduleRepository.getDsAnfTos();
    }
}
