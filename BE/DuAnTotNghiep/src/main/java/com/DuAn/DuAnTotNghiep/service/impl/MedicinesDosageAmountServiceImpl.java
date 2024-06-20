package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.MedicinesDosageAmount;
import com.DuAn.DuAnTotNghiep.model.request.MedicinesDosageAmountRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.MedicinesDosageAmountRepository;
import com.DuAn.DuAnTotNghiep.service.service.MedicinesDosageAmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicinesDosageAmountServiceImpl implements MedicinesDosageAmountService {

    @Autowired
    private MedicinesDosageAmountRepository medicinesDosageAmountRepository;

    @Override
    public MedicinesDosageAmount findByMedicinesDosageAmountId(int dosageAmountId) {
        return medicinesDosageAmountRepository.findById(dosageAmountId).orElse(null);
    }

    @Override
    public List<MedicinesDosageAmount> findAllMedicinesDosageAmounts() {
        return medicinesDosageAmountRepository.findAll() ;
    }

    @Override
    public List<MedicinesDosageAmount> findAllMedicinesDosageAmountsExceptDeleted() {
        return medicinesDosageAmountRepository.findAll().stream()
                .filter(medicinesDosageAmount -> !medicinesDosageAmount.isDeleted())
                .collect(Collectors.toList());
    }

    @Override
    public MedicinesDosageAmount saveMedicinesDosageAmount(MedicinesDosageAmountRequest dosageAmountRequest) {
        MedicinesDosageAmount dosageAmount = MedicinesDosageAmount.builder()
                .amount(dosageAmountRequest.getAmount())
                .description(dosageAmountRequest.getDescription())
                .build();
        medicinesDosageAmountRepository.save(dosageAmount);
        return dosageAmount;
    }

    @Override
    public MedicinesDosageAmount updateMedicinesDosageAmount(int dosageAmountId, MedicinesDosageAmountRequest dosageAmountRequest) {
        MedicinesDosageAmount dosageAmount = MedicinesDosageAmount.builder()
                .medicinesDosageAmountId(dosageAmountId)
                .amount(dosageAmountRequest.getAmount())
                .description(dosageAmountRequest.getDescription())
                .build();
        medicinesDosageAmountRepository.save(dosageAmount);
        return dosageAmount;
    }

    @Override
    public MessageResponse deleteMedicinesDosageAmount(int dosageAmountId) {
        try {
            medicinesDosageAmountRepository.deleteById(dosageAmountId) ;
            return new MessageResponse("Successfully") ;
        } catch (Exception e) {
            e.printStackTrace() ;
            return new MessageResponse("Failed") ;
        }
    }

    @Override
    public MessageResponse softDeleteMedicinesDosageAmount(int dosageAmountId) {
        try {
            MedicinesDosageAmount medicinesDosageAmount = medicinesDosageAmountRepository.findById(dosageAmountId)
                                                                  .orElseThrow(() -> new RuntimeException("medicines Dosage Amount not found")) ;
            medicinesDosageAmount.setDeleted(true) ;
            medicinesDosageAmountRepository.save(medicinesDosageAmount) ;
            return new MessageResponse("Successfully") ;
        } catch (Exception e) {
            e.printStackTrace() ;
            return new MessageResponse("Failed") ;
        }
    }
}
