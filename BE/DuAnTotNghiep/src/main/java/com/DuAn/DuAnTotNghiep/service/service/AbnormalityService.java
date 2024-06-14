package com.DuAn.DuAnTotNghiep.service.service;

import com.DuAn.DuAnTotNghiep.entities.Abnormality;
import com.DuAn.DuAnTotNghiep.entities.Doctor;
import com.DuAn.DuAnTotNghiep.model.request.AbnormalityRequest;
import com.DuAn.DuAnTotNghiep.model.request.DoctorRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;

import java.util.List;

public interface AbnormalityService {

    Abnormality findByAbnormalityId(int abnormalityId);

    List<Abnormality> findAll();

    Abnormality saveAbnormality(AbnormalityRequest abnormalityRequest);

    Abnormality updateAbnormality(int abnormalityId, AbnormalityRequest abnormalityRequest);

    MessageResponse delete(int abnormalityId);

    MessageResponse softDeleteAbnormality(int abnormalityId);
}
