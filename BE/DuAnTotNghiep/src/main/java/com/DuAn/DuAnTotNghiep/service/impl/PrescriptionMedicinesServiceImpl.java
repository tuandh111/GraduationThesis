package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.repositories.PrescriptionMedicinesRepository;
import com.DuAn.DuAnTotNghiep.service.service.PrescriptionMedicinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrescriptionMedicinesServiceImpl implements PrescriptionMedicinesService {
    @Autowired
    PrescriptionMedicinesRepository prescriptionMedicinesRepository;
}
