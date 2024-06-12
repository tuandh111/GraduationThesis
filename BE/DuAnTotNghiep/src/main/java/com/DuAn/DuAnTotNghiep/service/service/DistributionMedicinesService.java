package com.DuAn.DuAnTotNghiep.service.service;

import com.DuAn.DuAnTotNghiep.entities.DistributionMedicines;
import com.DuAn.DuAnTotNghiep.model.request.DistributionMedicinesRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;

import java.util.List;

public interface DistributionMedicinesService {
    DistributionMedicines findByDistributionMedicineId(int distributionMedicineId);

    List<DistributionMedicines> findAllDistributionMedicines();

    DistributionMedicines saveDistributionMedicine(DistributionMedicinesRequest distributionMedicineRequest);

    DistributionMedicines updateDistributionMedicine(int distributionMedicineId, DistributionMedicinesRequest distributionMedicineRequest);

    MessageResponse deleteDistributionMedicine(int distributionMedicineId);

    MessageResponse softDeleteDistributionMedicine(int distributionMedicineId);
}
