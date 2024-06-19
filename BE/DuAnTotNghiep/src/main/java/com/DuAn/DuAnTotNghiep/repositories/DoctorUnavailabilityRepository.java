package com.DuAn.DuAnTotNghiep.repositories;

import com.DuAn.DuAnTotNghiep.entities.DoctorUnavailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorUnavailabilityRepository extends JpaRepository<DoctorUnavailability,Integer> {
}
