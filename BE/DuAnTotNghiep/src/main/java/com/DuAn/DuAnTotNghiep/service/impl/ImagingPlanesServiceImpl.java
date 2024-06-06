package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.repositories.ImagingPlanesRepository;
import com.DuAn.DuAnTotNghiep.service.service.ImagingPlanesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImagingPlanesServiceImpl implements ImagingPlanesService {
    @Autowired
    ImagingPlanesRepository imagingPlanesRepository;
}
