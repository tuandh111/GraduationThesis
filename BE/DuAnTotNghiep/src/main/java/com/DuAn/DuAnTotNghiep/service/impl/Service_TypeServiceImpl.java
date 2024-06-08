package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.ServiceType;
import com.DuAn.DuAnTotNghiep.model.request.ServiceTypeRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.ServiceTypeRepository;
import com.DuAn.DuAnTotNghiep.service.service.ServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Service_TypeServiceImpl implements ServiceTypeService {
    @Autowired
    ServiceTypeRepository serviceTypeRepository;

    @Override
    public ServiceType findByServiceTypeId(int serviceTypeId) {
        return serviceTypeRepository.findById(serviceTypeId).orElseThrow(null);
    }

    @Override
    public List<ServiceType> findAllServiceType() {
        return serviceTypeRepository.findAll();
    }

    @Override
    public ServiceType saveServiceType(ServiceTypeRequest serviceTypeRequest) {
        var serviceType = ServiceType
                                  .builder()
                                  .typeName(serviceTypeRequest.getType())
                                  .description(serviceTypeRequest.getDescription())
                                  .build();
        serviceTypeRepository.save(serviceType);
        return serviceType;
    }

    @Override
    public ServiceType updateServiceType(int serviceTypeId, ServiceTypeRequest serviceTypeRequest) {
        var serviceType = ServiceType
                                  .builder()
                                  .service_TypeId(serviceTypeId)
                                  .typeName(serviceTypeRequest.getType())
                                  .description(serviceTypeRequest.getDescription())
                                  .build();
        serviceTypeRepository.save(serviceType);
        return serviceType;
    }

    @Override
    public MessageResponse delete(int serviceTypeId) {
        try {
            serviceTypeRepository.deleteById(serviceTypeId);
            return new MessageResponse("successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageResponse("fail");
        }
    }
}
