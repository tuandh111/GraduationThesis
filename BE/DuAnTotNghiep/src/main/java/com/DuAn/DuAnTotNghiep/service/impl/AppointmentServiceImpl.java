package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.Appointment;
import com.DuAn.DuAnTotNghiep.entities.Bill;
import com.DuAn.DuAnTotNghiep.model.request.AppointmentRequest;
import com.DuAn.DuAnTotNghiep.model.response.AppointmentWithServicesResponse;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.*;
import com.DuAn.DuAnTotNghiep.service.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    DentalStaffRepository dentalStaffRepository;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    AppointmentStatusRepository appointmentStatusRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    AppointmentTypeRepository appointmentTypeRepository;
    @Autowired
    AppointmentPatientRecordRepository appointmentPatientRecordRepository;

    @Autowired
    AppointmentServiceRepository appointmentServiceRepository;

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    BillRepository billRepository;

    @Override
    public Appointment findByAppointmentId(int appointmentId) {
        return appointmentRepository.findById(appointmentId).orElseThrow(null);
    }

    @Override
    public List<Appointment> findAllAppointment() {
        return appointmentRepository.findAll();
    }

    @Override
    public List<Appointment> findAllAppointmentExceptDeleted() {
        return appointmentRepository.findAll().stream().filter(appointment -> !appointment.isDeleted()).collect(Collectors.toList());
    }

    @Override
    public Appointment saveAppointment(AppointmentRequest appointmentRequest) {
        var appointment = Appointment.builder().doctor(doctorRepository.findById(appointmentRequest.getDoctorId()).orElse(null)).appointmentType(appointmentTypeRepository.findById(appointmentRequest.getAppointmentType()).orElse(null)).createAt(new Date()).appointmentStatus(appointmentStatusRepository.findById(appointmentRequest.getAppointmentStatus()).orElse(null)).appointmentPatientRecord(appointmentPatientRecordRepository.findById(appointmentRequest.getAppointmentPatientRecord()).orElse(null)).AppointmentDate(appointmentRequest.getAppointmentDate()).patient(patientRepository.findById(appointmentRequest.getPatientId()).orElse(null)).dentalStaff(dentalStaffRepository.findById(appointmentRequest.getDentalStaffId()).orElse(null)).note(appointmentRequest.getNote()).build();
        appointmentRepository.save(appointment);
        return appointment;
    }

    @Override
    public Appointment updateAppointment(int appointmentId, AppointmentRequest appointmentRequest) {
        var appointment = Appointment.builder().appointmentId(appointmentId).doctor(doctorRepository.findById(appointmentRequest.getDoctorId()).orElse(null)).appointmentType(appointmentTypeRepository.findById(appointmentRequest.getAppointmentType()).orElse(null)).createAt(appointmentRequest.getCreateAt()).appointmentStatus(appointmentStatusRepository.findById(appointmentRequest.getAppointmentStatus()).orElse(null)).appointmentPatientRecord(appointmentPatientRecordRepository.findById(appointmentRequest.getAppointmentPatientRecord()).orElse(null)).AppointmentDate(appointmentRequest.getAppointmentDate()).patient(patientRepository.findById(appointmentRequest.getPatientId()).orElse(null)).dentalStaff(dentalStaffRepository.findById(appointmentRequest.getDentalStaffId()).orElse(null)).note(appointmentRequest.getNote()).isDeleted(appointmentRequest.isDeleted()).build();
        appointmentRepository.save(appointment);
        return appointment;
    }

    @Override
    public MessageResponse delete(int appointmentId) {
        try {
            appointmentRepository.deleteById(appointmentId);
            return new MessageResponse("successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageResponse("fail");
        }
    }

    @Override
    public MessageResponse softDeleteAppointment(int appointmentId) {
        try {
            var appointment = appointmentRepository.findById(appointmentId).orElseThrow(() -> new RuntimeException("appointment not found"));
            appointment.setDeleted(true);
            appointmentRepository.save(appointment);
            return new MessageResponse("successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageResponse("fail");
        }
    }

    @Override
    public List<AppointmentWithServicesResponse> findAllAppointmentService() {
        List<Appointment> appointments = appointmentRepository.findAll()
                                                 .stream().filter(appointment -> !appointment.isDeleted()).collect(Collectors.toList());
        List<com.DuAn.DuAnTotNghiep.entities.AppointmentService> appointmentServices = appointmentServiceRepository.findAll()
                                                                                               .stream().filter(appointmentService -> !appointmentService.isDeleted()).collect(Collectors.toList());
        List<Bill> bills = billRepository.findAll()
                                   .stream()
                                   .filter(bill -> !bill.isDeleted())
                                   .collect(Collectors.toList());
        Map<Integer, List<com.DuAn.DuAnTotNghiep.entities.Service>> appointmentIdToServicesMap = appointmentServices.stream()
                                                                                                         .collect(Collectors.groupingBy(
                                                                                                                 appointmentService -> appointmentService.getAppointment().getAppointmentId(),
                                                                                                                 Collectors.mapping(com.DuAn.DuAnTotNghiep.entities.AppointmentService::getService, Collectors.toList())
                                                                                                         ));
        Set<Integer> appointmentIdsWithBills = bills.stream()
                                                       .map(bill -> bill.getAppointment().getAppointmentId())
                                                       .collect(Collectors.toSet());

        return appointments.stream()
                       .sorted((a1, a2) -> a2.getAppointmentDate().compareTo(a1.getAppointmentDate()))
                       .map(appointment -> new AppointmentWithServicesResponse(
                               appointment,
                               appointmentIdToServicesMap.getOrDefault(appointment.getAppointmentId(), new ArrayList<>()),
                               appointmentIdsWithBills.contains(appointment.getAppointmentId())
                       ))
                       .collect(Collectors.toList());
    }
    public AppointmentWithServicesResponse findAppointmentServiceByAppointmentId(int appointmentId) {
        // Retrieve all appointments and filter by non-deleted ones
        List<Appointment> appointments = appointmentRepository.findAll()
                                                 .stream()
                                                 .filter(appointment -> !appointment.isDeleted())
                                                 .filter(appointment -> appointment.getAppointmentId() == appointmentId) // Filter by appointmentId
                                                 .collect(Collectors.toList());

        // If no appointment is found, return null or throw an exception
        if (appointments.isEmpty()) {
            return null; // hoặc throw new EntityNotFoundException("Appointment not found");
        }

        Appointment appointment = appointments.get(0);

        // Retrieve all appointment services and filter by non-deleted ones
        List<com.DuAn.DuAnTotNghiep.entities.AppointmentService> appointmentServices = appointmentServiceRepository.findAll()
                                                                                               .stream()
                                                                                               .filter(appointmentService -> !appointmentService.isDeleted())
                                                                                               .collect(Collectors.toList());

        // Retrieve all bills and filter by non-deleted ones
        List<Bill> bills = billRepository.findAll()
                                   .stream()
                                   .filter(bill -> !bill.isDeleted())
                                   .collect(Collectors.toList());

        // Group appointment services by appointmentId
        Map<Integer, List<com.DuAn.DuAnTotNghiep.entities.Service>> appointmentIdToServicesMap = appointmentServices.stream()
                                                                                                         .collect(Collectors.groupingBy(
                                                                                                                 appointmentService -> appointmentService.getAppointment().getAppointmentId(),
                                                                                                                 Collectors.mapping(com.DuAn.DuAnTotNghiep.entities.AppointmentService::getService, Collectors.toList())
                                                                                                         ));

        // Collect appointmentIds with bills into a set
        Set<Integer> appointmentIdsWithBills = bills.stream()
                                                       .map(bill -> bill.getAppointment().getAppointmentId())
                                                       .collect(Collectors.toSet());

        // Create and return the response object
        return new AppointmentWithServicesResponse(
                appointment,
                appointmentIdToServicesMap.getOrDefault(appointment.getAppointmentId(), new ArrayList<>()),
                appointmentIdsWithBills.contains(appointment.getAppointmentId())
        );
    }


}
