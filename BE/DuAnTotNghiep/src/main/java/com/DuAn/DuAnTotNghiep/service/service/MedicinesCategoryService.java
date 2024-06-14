package com.DuAn.DuAnTotNghiep.service.service;

import com.DuAn.DuAnTotNghiep.entities.MedicineCategory;
import com.DuAn.DuAnTotNghiep.model.request.MedicineCategoryRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;

import java.util.List;

public interface MedicinesCategoryService {
    MedicineCategory findByMedicineCategoryId(int categoryId) ;

    List<MedicineCategory> findAllMedicineCategories() ;

    MedicineCategory saveMedicineCategory(MedicineCategoryRequest categoryRequest) ;

    MedicineCategory updateMedicineCategory(int categoryId, MedicineCategoryRequest categoryRequest) ;

    MessageResponse deleteMedicineCategory(int categoryId) ;

    MessageResponse softDeleteMedicineCategory(int categoryId) ;
}
