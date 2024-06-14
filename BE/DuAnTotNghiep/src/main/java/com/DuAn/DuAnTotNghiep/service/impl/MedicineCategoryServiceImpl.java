package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.MedicineCategory;
import com.DuAn.DuAnTotNghiep.model.request.MedicineCategoryRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.MedicinesCategoryRepository;
import com.DuAn.DuAnTotNghiep.service.service.MedicinesCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineCategoryServiceImpl implements MedicinesCategoryService {

    @Autowired
    private MedicinesCategoryRepository medicineCategoryRepository;

    @Override
    public MedicineCategory findByMedicineCategoryId(int categoryId) {
        return medicineCategoryRepository.findById(categoryId).orElse(null);
    }

    @Override
    public List<MedicineCategory> findAllMedicineCategories() {
        return medicineCategoryRepository.findAll();
    }

    @Override
    public MedicineCategory saveMedicineCategory(MedicineCategoryRequest categoryRequest) {
        MedicineCategory category = MedicineCategory.builder()
                .name(categoryRequest.getName())
                .description(categoryRequest.getDescription())
                .build();
        medicineCategoryRepository.save(category);
        return category;
    }

    @Override
    public MedicineCategory updateMedicineCategory(int categoryId, MedicineCategoryRequest categoryRequest) {
        MedicineCategory category = MedicineCategory.builder()
                .medicineCategoryId(categoryId)
                .name(categoryRequest.getName())
                .description(categoryRequest.getDescription())
                .build();
        medicineCategoryRepository.save(category);
        return category;
    }

    @Override
    public MessageResponse deleteMedicineCategory(int categoryId) {
        try {
            medicineCategoryRepository.deleteById(categoryId) ;
            return new MessageResponse("Successfully") ;
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageResponse("Failed") ;
        }
    }

    @Override
    public MessageResponse softDeleteMedicineCategory(int categoryId) {
        try {
            MedicineCategory medicineCategory = medicineCategoryRepository.findById(categoryId).orElseThrow(null) ;
            medicineCategory.setDeleted(true) ;
            medicineCategoryRepository.save(medicineCategory) ;
            return new MessageResponse("Successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageResponse("Failed");
        }
    }
}
