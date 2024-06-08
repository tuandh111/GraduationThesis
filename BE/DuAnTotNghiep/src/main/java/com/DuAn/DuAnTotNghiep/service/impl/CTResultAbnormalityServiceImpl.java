package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.CTResultAbnormality;
import com.DuAn.DuAnTotNghiep.model.request.CTResultAbnormalityRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.AbnormalityRepository;
import com.DuAn.DuAnTotNghiep.repositories.AppointmentCTResultRepository;
import com.DuAn.DuAnTotNghiep.repositories.CTResultAbnormalityRepository;
import com.DuAn.DuAnTotNghiep.service.service.CTResultAbnormalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CTResultAbnormalityServiceImpl implements CTResultAbnormalityService {
    @Autowired
    CTResultAbnormalityRepository ctResultAbnormalityRepository;
    @Autowired
    AppointmentCTResultRepository appointmentCTResultRepository;
    @Autowired
    AbnormalityRepository abnormalityRepository;

    @Override
    public CTResultAbnormality findByCTResultAbnormalityId(int cTResultAbnormalityId) {
        return ctResultAbnormalityRepository.findById(cTResultAbnormalityId).orElseThrow(null);
    }

    @Override
    public List<CTResultAbnormality> findAll() {
        return ctResultAbnormalityRepository.findAll();
    }

    @Override
    public CTResultAbnormality saveCTResultAbnormality(CTResultAbnormalityRequest ctResultAbnormalityRequest) {
        var cTResultAbnormality = CTResultAbnormality
                                          .builder()
                                          .Description(ctResultAbnormalityRequest.getDescription())
                                          .abnormality(abnormalityRepository.findById(ctResultAbnormalityRequest.getAbnormalityId()).orElseThrow(null))
                                          .appointmentCTResult(appointmentCTResultRepository.findById(ctResultAbnormalityRequest.getAppointmentCTResult()).orElseThrow(null))
                                          .build();
        ctResultAbnormalityRepository.save(cTResultAbnormality);
        return cTResultAbnormality;
    }

    @Override
    public CTResultAbnormality updateCTResultAbnormality(int cTResultAbnormalityId, CTResultAbnormalityRequest ctResultAbnormalityRequest) {
        var cTResultAbnormality = CTResultAbnormality
                                          .builder()
                                          .cTResultAbnormalityId(cTResultAbnormalityId)
                                          .Description(ctResultAbnormalityRequest.getDescription())
                                          .abnormality(abnormalityRepository.findById(ctResultAbnormalityRequest.getAbnormalityId()).orElseThrow(null))
                                          .appointmentCTResult(appointmentCTResultRepository.findById(ctResultAbnormalityRequest.getAppointmentCTResult()).orElseThrow(null))
                                          .build();
        ctResultAbnormalityRepository.save(cTResultAbnormality);
        return cTResultAbnormality;
    }

    @Override
    public MessageResponse delete(int cTResultAbnormalityId) {
        try {
            ctResultAbnormalityRepository.deleteById(cTResultAbnormalityId);
            return new MessageResponse("successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageResponse("fail");
        }
    }
}
