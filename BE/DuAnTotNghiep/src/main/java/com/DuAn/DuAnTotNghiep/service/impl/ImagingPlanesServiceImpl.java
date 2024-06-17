package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.Abnormality;
import com.DuAn.DuAnTotNghiep.entities.ImagingPlanes;
import com.DuAn.DuAnTotNghiep.model.request.ImagingPlanesRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.ImagingPlanesRepository;
import com.DuAn.DuAnTotNghiep.service.service.ImagingPlanesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImagingPlanesServiceImpl implements ImagingPlanesService {
    @Autowired
    ImagingPlanesRepository imagingPlanesRepository;

    @Override
    public ImagingPlanes findByImagingPlanesId(int imagingPlanesId) {
        return imagingPlanesRepository.findById(imagingPlanesId).orElseThrow(null);
    }

    @Override
    public List<ImagingPlanes> findAll() {
        return imagingPlanesRepository.findAll().stream()
                       .filter(imagingPlanes -> !imagingPlanes.isDeleted())
                       .collect(Collectors.toList());
    }

    @Override
    public ImagingPlanes saveImagingPlanes(ImagingPlanesRequest imagingPlanesRequest) {
        var imagingPlanes = ImagingPlanes
                                    .builder()
                                    .name(imagingPlanesRequest.getName())
                                    .description(imagingPlanesRequest.getDescription())
                                    .build();
        imagingPlanesRepository.save(imagingPlanes);
        return imagingPlanes;
    }

    @Override
    public ImagingPlanes updateImagingPlanes(int imagingPlanesId, ImagingPlanesRequest imagingPlanesRequest) {
        var imagingPlanes = ImagingPlanes
                                  .builder()
                                  .imagingPlanesId(imagingPlanesId)
                                  .name(imagingPlanesRequest.getName())
                                  .description(imagingPlanesRequest.getDescription())
                                  .build();
        imagingPlanesRepository.save(imagingPlanes);
        return imagingPlanes;
    }

    @Override
    public MessageResponse delete(int imagingPlanesId) {
        try {
            imagingPlanesRepository.deleteById(imagingPlanesId);
            return new MessageResponse("successfully");
        }catch (Exception e){
            return new MessageResponse("fail");
        }
    }
}
