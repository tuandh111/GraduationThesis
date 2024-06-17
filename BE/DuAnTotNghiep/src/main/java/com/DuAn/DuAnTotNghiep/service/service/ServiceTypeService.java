package com.DuAn.DuAnTotNghiep.service.service;

import com.DuAn.DuAnTotNghiep.entities.ServiceType;
import com.DuAn.DuAnTotNghiep.model.request.ServiceTypeRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;

import java.util.List;

public interface ServiceTypeService {
    ServiceType findByServiceTypeId(int serviceTypeId);

    List<ServiceType> findAllServiceType();

    List<ServiceType> findAllServiceTypeExceptDeleted();

    ServiceType saveServiceType(ServiceTypeRequest serviceTypeRequest);

    ServiceType updateServiceType(int serviceTypeId, ServiceTypeRequest serviceTypeRequest);

    MessageResponse delete(int serviceTypeId);

    MessageResponse sortDeleteServiceType(int serviceTypeId);
}
