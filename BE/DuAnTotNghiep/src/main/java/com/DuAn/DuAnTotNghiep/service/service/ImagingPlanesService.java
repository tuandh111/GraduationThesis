package com.DuAn.DuAnTotNghiep.service.service;

import com.DuAn.DuAnTotNghiep.entities.Abnormality;
import com.DuAn.DuAnTotNghiep.entities.ImagingPlanes;
import com.DuAn.DuAnTotNghiep.model.request.AbnormalityRequest;
import com.DuAn.DuAnTotNghiep.model.request.ImagingPlanesRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;

import java.util.List;

public interface ImagingPlanesService {
    ImagingPlanes findByImagingPlanesId(int imagingPlanesId);

    List<ImagingPlanes> findAllImagingPlanes();

    List<ImagingPlanes> findAllImagingPlanesExceptDeleted();

    ImagingPlanes saveImagingPlanes(ImagingPlanesRequest imagingPlanesRequest);

    ImagingPlanes updateImagingPlanes(int imagingPlanesId, ImagingPlanesRequest imagingPlanesRequest);

    MessageResponse delete(int imagingPlanesId);
}
