package com.DuAn.DuAnTotNghiep.service.service;

import com.DuAn.DuAnTotNghiep.entities.DistributionMedicines;
import com.DuAn.DuAnTotNghiep.model.request.DistributionMedicinesRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;

import java.util.List;

public interface DistributionMedicinesService {
    DistributionMedicines findByDistributionMedicinesId(int distributionMedicineId) ;

    List<DistributionMedicines> findAllDistributionMedicines() ;

    List<DistributionMedicines> findAllDistributionMedicinesExceptDeleted() ;

    DistributionMedicines saveDistributionMedicines(DistributionMedicinesRequest distributionMedicineRequest) ;

    DistributionMedicines updateDistributionMedicines(int distributionMedicineId, DistributionMedicinesRequest distributionMedicineRequest) ;

    MessageResponse deleteDistributionMedicines(int distributionMedicineId) ;

    MessageResponse softDeleteDistributionMedicines(int distributionMedicineId) ;
}
