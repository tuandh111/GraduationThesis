package com.DuAn.DuAnTotNghiep.repositories;

import com.DuAn.DuAnTotNghiep.entities.MedicineCategory;
import com.DuAn.DuAnTotNghiep.entities.Medicines;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicinesCategoryRepository extends JpaRepository<MedicineCategory,Integer> {

}
