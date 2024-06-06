package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.repositories.FrequencyMedicinesRepository;
import com.DuAn.DuAnTotNghiep.service.service.FrequencyMedicinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FrequencyMedicinesServiceImpl implements FrequencyMedicinesService {
    @Autowired
    FrequencyMedicinesRepository frequencyMedicinesRepository;
}
