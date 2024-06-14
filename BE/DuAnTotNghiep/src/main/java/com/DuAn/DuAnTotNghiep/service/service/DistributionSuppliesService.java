package com.DuAn.DuAnTotNghiep.service.service;

import com.DuAn.DuAnTotNghiep.entities.DistributionSupplies;
import com.DuAn.DuAnTotNghiep.entities.DoctorSchedule;
import com.DuAn.DuAnTotNghiep.model.request.DistributionSuppliesRequest;
import com.DuAn.DuAnTotNghiep.model.request.DoctorRequest;
import com.DuAn.DuAnTotNghiep.model.request.DoctorScheduleRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;

import java.util.List;

public interface DistributionSuppliesService {
    DistributionSupplies findByDistributionSuppliesId(int distributionSuppliesId);

    List<DistributionSupplies> findAll();

    DistributionSupplies saveDistributionSupplies(DistributionSuppliesRequest distributionSuppliesRequest);

    DistributionSupplies updateDistributionSupplies(int distributionSuppliesId, DistributionSuppliesRequest distributionSuppliesRequest);

    MessageResponse delete(int distributionSuppliesId);

    MessageResponse sortDeleteDistributionSupplies(int distributionSuppliesId);
}
