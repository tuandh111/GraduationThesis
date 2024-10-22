package com.DuAn.DuAnTotNghiep.service.service;

import com.DuAn.DuAnTotNghiep.entities.Service;
import com.DuAn.DuAnTotNghiep.model.request.ServiceRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceService {
    Service findByServiceId(int serviceId);

    List<Service> findAllService();

    List<Service> findAllServiceExceptDeleted();

    Service saveService(ServiceRequest serviceRequest);

    Service updateService(int serviceId, ServiceRequest serviceRequest);

    MessageResponse delete(int serviceId);

    MessageResponse softDeleteService(int serviceId);

    List<Object> findServiceByDentalIssues(List<Integer> ids);
}
