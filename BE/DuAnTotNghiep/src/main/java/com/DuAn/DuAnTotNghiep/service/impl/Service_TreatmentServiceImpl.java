package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.ServiceTreatment;
import com.DuAn.DuAnTotNghiep.model.request.ServiceTreatmentRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.ServiceRepository;
import com.DuAn.DuAnTotNghiep.repositories.ServiceTreatmentRepository;
import com.DuAn.DuAnTotNghiep.repositories.TreatmentRepository;
import com.DuAn.DuAnTotNghiep.service.service.ServiceTreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Service_TreatmentServiceImpl implements ServiceTreatmentService {
    @Autowired
    ServiceTreatmentRepository serviceTreatmentRepository;
    @Autowired
    ServiceRepository serviceRepository;
    @Autowired
    TreatmentRepository treatmentRepository;
    @Override
    public ServiceTreatment findByServiceTreatment(int serviceTreatment) {
        return serviceTreatmentRepository.findById(serviceTreatment).orElseThrow(null);
    }

    @Override
    public List<ServiceTreatment> findAllServiceTreatment() {
        return serviceTreatmentRepository.findAll();
    }

    @Override
    public ServiceTreatment saveServiceTreatment(ServiceTreatmentRequest serviceTreatmentRequest) {
        var serviceTreatment = ServiceTreatment
                                       .builder()
                                       .service(serviceRepository.findById(serviceTreatmentRequest.getServiceId()).orElseThrow(null))
                                       .treatment(treatmentRepository.findById(serviceTreatmentRequest.getTreatment()).orElseThrow(null))
                                       .Description(serviceTreatmentRequest.getDescription())
                                       .build();
        serviceTreatmentRepository.save(serviceTreatment);
        return serviceTreatment;
    }

    @Override
    public ServiceTreatment updateServiceTreatment(int serviceTreatmentId, ServiceTreatmentRequest serviceTreatmentRequest) {
        var serviceTreatment = ServiceTreatment
                                       .builder()
                                       .service_TreatmentId(serviceTreatmentId)
                                       .service(serviceRepository.findById(serviceTreatmentRequest.getServiceId()).orElseThrow(null))
                                       .treatment(treatmentRepository.findById(serviceTreatmentRequest.getTreatment()).orElseThrow(null))
                                       .Description(serviceTreatmentRequest.getDescription())
                                       .build();
        serviceTreatmentRepository.save(serviceTreatment);
        return serviceTreatment;
    }

    @Override
    public MessageResponse delete(int serviceTreatmentId) {
        try {
            serviceTreatmentRepository.deleteById(serviceTreatmentId);
            return new MessageResponse("successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageResponse("fail");
        }
    }
}
