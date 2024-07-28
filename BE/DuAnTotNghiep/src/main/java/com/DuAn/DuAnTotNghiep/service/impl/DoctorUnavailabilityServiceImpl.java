package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.DoctorSchedule;
import com.DuAn.DuAnTotNghiep.entities.DoctorUnavailability;
import com.DuAn.DuAnTotNghiep.model.request.DoctorUnavailabilityRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.AppointmentRepository;
import com.DuAn.DuAnTotNghiep.repositories.DoctorUnavailabilityRepository;
import com.DuAn.DuAnTotNghiep.repositories.TimeOfShiftRepository;
import com.DuAn.DuAnTotNghiep.service.service.DoctorUnavailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DoctorUnavailabilityServiceImpl implements DoctorUnavailabilityService {

    @Autowired
    DoctorUnavailabilityRepository doctorUnavailabilityRepository ;

    @Autowired
    TimeOfShiftRepository timeOfShiftRepository ;

    @Autowired
    AppointmentRepository appointmentRepository ;

    @Override
    public DoctorUnavailability findDoctorUnavailabilityById(int doctorUnavailabilityId) {
        return doctorUnavailabilityRepository.findById(doctorUnavailabilityId).orElseThrow(null) ;
    }

    @Override
    public List<DoctorUnavailability> findAllDoctorUnavailability() {
        return doctorUnavailabilityRepository.findAll().stream()
                .sorted(Comparator.comparing(DoctorUnavailability::getDate).reversed()
                        .thenComparing(du -> du.getTimeOfShift().getBeginTime()))
                .collect(Collectors.toList());
    }

    @Override
    public List<DoctorUnavailability> findAllDoctorUnavailabilityByDoctor(int doctorId) {
        return doctorUnavailabilityRepository.getDoctorUnavailabilityByDoctor(doctorId);
    }

    @Override
    public List<Object> findShiftOfDoctorFromDoctorUnavailability(Date date, int doctorId) {
        return doctorUnavailabilityRepository.getShiftOfDoctorFromDoctorUnavailability(date,doctorId);
    }

    @Override
    public List<DoctorUnavailability> findDoctorUnavailabilityByAppId(Integer appointmentId) {
        return doctorUnavailabilityRepository.getDoctorUnavailabilityByAppId(appointmentId);
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
                .date(doctorUnavailabilityRequest.getDate())
                .isDeleted(false)
                .appointment(appointmentRepository.findById(doctorUnavailabilityRequest.getAppointmentId()).orElse(null))
                .build() ;
        doctorUnavailabilityRepository.save(doctorUnavailability) ;
        return doctorUnavailability;
    }

    @Override
    public DoctorUnavailability updateDoctorUnavailability(int doctorUnavailabilityId, DoctorUnavailabilityRequest doctorUnavailabilityRequest) {
        var doctorUnavailability = DoctorUnavailability.builder()
                .doctorUnavailabilityId(doctorUnavailabilityId)
                .description(doctorUnavailabilityRequest.getDescription())
                .date(doctorUnavailabilityRequest.getDate())
                .isDeleted(doctorUnavailabilityRequest.isDeleted())
                .timeOfShift(timeOfShiftRepository.findById(doctorUnavailabilityRequest.getTimeOfShiftId()).orElse(null))
                .appointment(appointmentRepository.findById(doctorUnavailabilityRequest.getAppointmentId()).orElse(null))
                .build() ;
        doctorUnavailabilityRepository.save(doctorUnavailability) ;
        return doctorUnavailability;
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

    @Override
    public List<Object> findDistinctDateOfDuByTimeRange(Date startDate, Date endDate, Integer doctorId) {
        return doctorUnavailabilityRepository.getDistinctDateOfDuByTimeRange(startDate,endDate,doctorId);
    }

    @Override
    public List<DoctorUnavailability> findDoctorUnavailabilityByDate(Date date) {
        return doctorUnavailabilityRepository.getDoctorUnavailabilityByDate(date);
    }

    @Override
    public Map<Date, List<DoctorUnavailability>> findDUByTimeRangeAndDateMap(Date startDate, Date endDate, Integer doctorId) {
        Map<Date, List<DoctorUnavailability>> duDateMap=new HashMap<>();
        List<Object> dates=this.findDistinctDateOfDuByTimeRange(startDate,endDate,doctorId);
        for (Object object : dates){
            Date date = (Date) object;
            List<DoctorUnavailability> listDu=this.findDoctorUnavailabilityByDate(date);
            duDateMap.put(date,listDu);
        }
        TreeMap<Date, List<DoctorUnavailability>> sortedMap = new TreeMap<>(Collections.reverseOrder());
        sortedMap.putAll(duDateMap);

        return sortedMap;
    }


}
