package com.DuAn.DuAnTotNghiep.repositories;

import com.DuAn.DuAnTotNghiep.entities.Abnormality;
import com.DuAn.DuAnTotNghiep.entities.MedicinesDosageUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicinesDosageUnitRepository extends JpaRepository<MedicinesDosageUnit,Integer> {
}
