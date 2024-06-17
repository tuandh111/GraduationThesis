package com.DuAn.DuAnTotNghiep.service.service;

import com.DuAn.DuAnTotNghiep.entities.ServiceTreatment;
import com.DuAn.DuAnTotNghiep.model.request.ServiceTreatmentRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;

import java.util.List;

public interface ServiceTreatmentService {
    ServiceTreatment findByServiceTreatment(int serviceTreatment);

    List<ServiceTreatment> findAllServiceTreatment();

    List<ServiceTreatment> findAllServiceTreatmentExceptDeleted();

    ServiceTreatment saveServiceTreatment(ServiceTreatmentRequest serviceTreatmentRequest);

    ServiceTreatment updateServiceTreatment(int serviceTreatmentId, ServiceTreatmentRequest serviceTreatmentRequest);

    MessageResponse delete(int serviceTreatmentId);

    MessageResponse sortDeleteServiceTreatment(int serviceTreatmentId);
}
