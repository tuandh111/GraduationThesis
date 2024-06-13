package com.DuAn.DuAnTotNghiep.service.service;

import com.DuAn.DuAnTotNghiep.entities.Abnormality;
import com.DuAn.DuAnTotNghiep.entities.CTResultAbnormality;
import com.DuAn.DuAnTotNghiep.model.request.AbnormalityRequest;
import com.DuAn.DuAnTotNghiep.model.request.CTResultAbnormalityRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;

import java.util.List;

public interface CTResultAbnormalityService {
    CTResultAbnormality findByCTResultAbnormalityId(int cTResultAbnormalityId);

    List<CTResultAbnormality> findAll();

    CTResultAbnormality saveCTResultAbnormality(CTResultAbnormalityRequest ctResultAbnormalityRequest);

    CTResultAbnormality updateCTResultAbnormality(int cTResultAbnormalityId, CTResultAbnormalityRequest ctResultAbnormalityRequest);

    MessageResponse delete(int cTResultAbnormalityId);

    MessageResponse sortDeleteCTResultAbnormality(int cTResultAbnormalityId);
}