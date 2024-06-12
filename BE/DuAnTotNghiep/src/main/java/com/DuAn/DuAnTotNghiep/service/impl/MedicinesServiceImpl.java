package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.repositories.MedicinesRepository;
import com.DuAn.DuAnTotNghiep.service.service.MedicinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicinesServiceImpl implements MedicinesService {
    @Autowired
    MedicinesRepository medicinesRepository;
}
