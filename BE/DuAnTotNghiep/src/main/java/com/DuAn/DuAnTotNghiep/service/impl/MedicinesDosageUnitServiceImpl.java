package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.repositories.MedicinesDosageUnitRepository;
import com.DuAn.DuAnTotNghiep.service.service.MedicinesDosageUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicinesDosageUnitServiceImpl implements MedicinesDosageUnitService {
    @Autowired
    MedicinesDosageUnitRepository medicinesDosageUnitRepository;
}
