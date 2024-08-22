package com.DuAn.DuAnTotNghiep.repositories;

import java.util.List;
import java.util.Optional;

import com.DuAn.DuAnTotNghiep.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  Optional<User> findByEmail(String email);

  @Query("select u FROM  User u "+
          "where (:doctorId IS null  or u.doctor.doctorId=:doctorId) " +
          "AND (:patientId IS null  or u.patient.patientId=:patientId) " +
          "AND (:dentalStaffId IS null  or u.dentalStaff.dentalStaffId=:dentalStaffId) " +
          "AND u.isDeleted=:isDeleted")
  List<User> getUserByObject(@Param("doctorId") Integer doctorId, @Param("patientId") Integer patientId, @Param("dentalStaffId") Integer dentalStaffId,@Param("isDeleted") boolean isDeleted);

  Optional<User> findByPatient_PatientId(Integer patientId);

}
