package com.DuAn.DuAnTotNghiep.service.service;

import com.DuAn.DuAnTotNghiep.entities.DentalSupplies;
import com.DuAn.DuAnTotNghiep.entities.DistributionSupplies;
import com.DuAn.DuAnTotNghiep.model.request.DentalSuppliesRequest;
import com.DuAn.DuAnTotNghiep.model.request.DistributionSuppliesRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;

import java.util.List;

public interface DentalSuppliesService {
    DentalSupplies findByDentalSuppliesId(int dentalSuppliesId);

    List<DentalSupplies> findAll();

    DentalSupplies saveDentalSupplies(DentalSuppliesRequest dentalSuppliesRequest);

    DentalSupplies updateDentalSupplies(int dentalSuppliesId, DentalSuppliesRequest dentalSuppliesRequest);

    MessageResponse delete(int dentalSuppliesId);
}
