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


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                                                 .stream()
                                                 .filter(appointment -> !appointment.isDeleted() && appointment.getBills() != null)
                                                 .collect(Collectors.toList());

        List<com.DuAn.DuAnTotNghiep.entities.AppointmentService> appointmentServices = appointmentServiceRepository.findAll()
                                                               .stream()
                                                               .filter(appointmentService -> !appointmentService.isDeleted())
                                                               .collect(Collectors.toList());


        List<Bill> bills = billRepository.findAll()
                                   .stream()
                                   .filter(bill -> !bill.isDeleted())
                                   .collect(Collectors.toList());

        Map<Integer, List<com.DuAn.DuAnTotNghiep.entities.AppointmentService>> appointmentIdToServicesMap = appointmentServices.stream()
                                                                                    .collect(Collectors.groupingBy(
                                                                                            appointmentService -> appointmentService.getAppointment().getAppointmentId(),
                                                                                            Collectors.toList()
                                                                                    ));

        String paidStatus = "Đã thanh toán";
        Set<Integer> paidBillIds = bills.stream()
                                           .filter(bill -> paidStatus.equalsIgnoreCase(bill.getStatus()))
                                           .map(bill -> bill.getAppointments().getAppointmentId())
                                           .collect(Collectors.toSet());
        Map<Integer, Integer> appointmentIdToBillIdMap = bills.stream()
                                                                 .collect(Collectors.toMap(
                                                                         bill -> bill.getAppointments().getAppointmentId(),
                                                                         Bill::getBillId
                                                                 ));
        return appointments.stream()
                       .sorted((a1, a2) -> a2.getAppointmentDate().compareTo(a1.getAppointmentDate()))
                       .map(appointment -> {
                           List<com.DuAn.DuAnTotNghiep.entities.AppointmentService> appointmentServicesForAppointment = appointmentIdToServicesMap.getOrDefault(appointment.getAppointmentId(), new ArrayList<>());

                           List<com.DuAn.DuAnTotNghiep.entities.Service> servicesWithPrices = appointmentServicesForAppointment.stream()
                                                                      .map(appointmentService -> {
                                                                          com.DuAn.DuAnTotNghiep.entities.Service service = appointmentService.getService();
                                                                          double calculatedPrice = appointmentService.getPrice() * appointmentService.getQuantity(); // Tính giá
                                                                          service.setPrice(calculatedPrice); // Gán giá vào Service
                                                                          return service;
                                                                      })
                                                                      .collect(Collectors.toList());
                           Integer billId = appointmentIdToBillIdMap.get(appointment.getAppointmentId());

                           return new AppointmentWithServicesResponse(
                                   appointment,
                                   servicesWithPrices,
                                   billId,
                                   paidBillIds.contains(appointment.getAppointmentId())
                           );
                       })
                       .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentWithServicesResponse> findAllBillCancel() {
        List<Bill> canceledBills = billRepository.findAll()
                                           .stream()
                                           .filter(bill -> !bill.isDeleted() && "Đã hủy".equalsIgnoreCase(bill.getStatus()))
                                           .collect(Collectors.toList());

        Set<Integer> canceledBillAppointmentIds = canceledBills.stream()
                                                          .map(bill -> bill.getAppointments().getAppointmentId())
                                                          .collect(Collectors.toSet());
//        List<Appointment> appointments = appointmentRepository.findAll()
//                                                 .stream()
//                                                 .filter(appointment -> !appointment.isDeleted()
//                                                                                && appointment.getBills() != null
//                                                                                && appointment.getBills().stream().anyMatch(bill -> canceledBillAppointmentIds.contains(bill.getAppointment().getAppointmentId())))
//                                                 .collect(Collectors.toList());
        List<Appointment> appointments = appointmentRepository.findAll()
                .stream()
                .filter(appointment -> !appointment.isDeleted()
                        && appointment.getBills() != null
                        && canceledBillAppointmentIds.contains(appointment.getBills().getAppointments().getAppointmentId()))
                .collect(Collectors.toList());
        List<com.DuAn.DuAnTotNghiep.entities.AppointmentService> appointmentServices = appointmentServiceRepository.findAll()
                                                                                               .stream().filter(appointmentService -> !appointmentService.isDeleted()).collect(Collectors.toList());
        Map<Integer, List<com.DuAn.DuAnTotNghiep.entities.Service>> appointmentIdToServicesMap = appointmentServices.stream()
                                                                                                         .collect(Collectors.groupingBy(
                                                                                                                 appointmentService -> appointmentService.getAppointment().getAppointmentId(),
                                                                                                                 Collectors.mapping(com.DuAn.DuAnTotNghiep.entities.AppointmentService::getService, Collectors.toList())
                                                                                                         ));

        return appointments.stream()
                       .sorted((a1, a2) -> a2.getAppointmentDate().compareTo(a1.getAppointmentDate()))
                       .map(appointment -> new AppointmentWithServicesResponse(
                               appointment, appointmentIdToServicesMap.getOrDefault(appointment.getAppointmentId(), new ArrayList<>()),1,
                               false
                       ))
                       .collect(Collectors.toList());
    }
    public AppointmentWithServicesResponse findAppointmentServiceByAppointmentId(int appointmentId) {
        // Retrieve all appointments and filter by non-deleted ones
        List<AppointmentWithServicesResponse> appointmentWithServicesResponseList =findAllAppointmentService();
        for (AppointmentWithServicesResponse appoimentList: appointmentWithServicesResponseList){
            if(appoimentList.getAppointment().getAppointmentId() == appointmentId){
                return appoimentList;
            }
        }
        return null;
    }
    public List<AppointmentWithServicesResponse> findAllAppointmentService(String startDate, String endDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date start;
        Date end;
        try {
            start = dateFormat.parse(startDate);
            end = dateFormat.parse(endDate);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use dd/MM/yyyy.");
        }

        List<Appointment> appointments = appointmentRepository.findAll()
                                                 .stream()
                                                 .filter(appointment -> !appointment.isDeleted() &&
                                                                                !appointment.getAppointmentDate().before(start) &&
                                                                                !appointment.getAppointmentDate().after(end))
                                                 .collect(Collectors.toList());

        List<com.DuAn.DuAnTotNghiep.entities.AppointmentService> appointmentServices = appointmentServiceRepository.findAll()
                                                                                               .stream().filter(appointmentService -> !appointmentService.isDeleted())
                                                                                               .collect(Collectors.toList());

        List<Bill> bills = billRepository.findAll()
                                   .stream()
                                   .filter(bill -> !bill.isDeleted())
                                   .collect(Collectors.toList());

        Map<Integer, List<com.DuAn.DuAnTotNghiep.entities.Service>> appointmentIdToServicesMap = appointmentServices.stream()
                                                                                                         .collect(Collectors.groupingBy(
                                                                                                                 appointmentService -> appointmentService.getAppointment().getAppointmentId(),
                                                                                                                 Collectors.mapping(com.DuAn.DuAnTotNghiep.entities.AppointmentService::getService, Collectors.toList())
                                                                                                         ));

        String paidStatus = "Đã thanh toán";
        Set<Integer> paidBillIds = bills.stream()
                                           .filter(bill -> paidStatus.equalsIgnoreCase(bill.getStatus()))
                                           .map(Bill::getBillId)
                                           .collect(Collectors.toSet());

        return appointments.stream()
                       .sorted((a1, a2) -> a2.getAppointmentDate().compareTo(a1.getAppointmentDate()))
                       .map(appointment -> new AppointmentWithServicesResponse(
                               appointment,
                               appointmentIdToServicesMap.getOrDefault(appointment.getAppointmentId(), new ArrayList<>()),1,
                               paidBillIds.contains(appointment.getAppointmentId())
                       ))
                       .collect(Collectors.toList());
    }


    public List<Object> findAllDateOfAppointment() {
        return appointmentRepository.getAllDateOfAppointment();
    }

    @Override
    public List<Appointment> findAppointmentByDate(Date date) {
        return appointmentRepository.getAppointmentByDate(date);
    }

    @Override
    public List<Appointment> findAllAppByTimeRange(Date startDate, Date endDate) {
        return appointmentRepository.getAllAppByTimeRange(startDate,endDate);
    }

    @Override
    public Map<Date, List<Appointment>> findAllAppGroupByDate(Date startDate, Date endDate, List<Integer> patientIds,List<Integer> doctorIds) {
        Map<Date, List<Appointment>> mapApps = new HashMap<>();

        List<Object> dates = this.findAllDateOfAppointment();

        if (endDate == null && startDate != null) {
            endDate = startDate;
        }

        final Date finalStartDate = startDate;
        final Date finalEndDate = endDate;


        if (finalStartDate != null) {
            dates = dates.stream()
                    .map(date -> (Date) date)
                    .filter(date -> !date.before(finalStartDate) && !date.after(finalEndDate))
                    .collect(Collectors.toList());
        }

        for (Object date : dates){
            List<Appointment> apps = this.findAppointmentByDate((Date) date);
            if (patientIds != null && !patientIds.isEmpty()) {
                apps = apps.stream()
                        .filter(app -> patientIds.contains(app.getPatient().getPatientId()))
                        .collect(Collectors.toList());
            }
            if (doctorIds != null && !doctorIds.isEmpty()) {
                apps = apps.stream()
                        .filter(app -> doctorIds.contains(app.getDoctor().getDoctorId()))
                        .collect(Collectors.toList());
            }
            mapApps.put((Date) date,apps);
        }


        return mapApps;
    }

    @Override
    public List<Appointment> findAllAppByPatient(Date now, Integer patientId) {
        return appointmentRepository.getAllAppByPatient(now,patientId);
    }

    @Override
    public List<Appointment> findAppointmentsByDateMonthYear(Integer date, Integer month, Integer year) {
        return appointmentRepository.getAppointmentsByDateMonthYear(date,month,year);
    }

    @Override
    public List<Appointment> findAppointmentWithOutBill(Integer appStatus) {
        return appointmentRepository.getAppointmentWithOutBill(appStatus);
    }

    @Override
    public List<Appointment> findAppointmentWithOutCtresult() {
        return appointmentRepository.getAppointmentWithOutCtresult();
    }

}
