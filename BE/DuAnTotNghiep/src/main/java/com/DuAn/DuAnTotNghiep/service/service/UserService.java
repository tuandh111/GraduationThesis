package com.DuAn.DuAnTotNghiep.service.service;

import com.DuAn.DuAnTotNghiep.entities.User;
import com.DuAn.DuAnTotNghiep.model.request.ChangePasswordRequest;
import com.DuAn.DuAnTotNghiep.model.request.PatientAndUserRequest;
import com.DuAn.DuAnTotNghiep.model.request.PatientRequest;
import com.DuAn.DuAnTotNghiep.model.request.UpdatePasswordRequest;
import com.DuAn.DuAnTotNghiep.model.response.ForgotPasswordResponse;
import org.springframework.data.repository.query.Param;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
//Khai bảo tất cả các phuương thức cần sử dụng trong đây nhé!
public interface UserService {

    void changePassword(ChangePasswordRequest request, Principal connectedUser);

    User updateUser(User request);

    List<User> findAll();

    Optional<User> findByEmail(String email);


    void updatePassword(UpdatePasswordRequest updatePasswordRequest);


    boolean checkUserByAnObject(Integer doctorId,Integer patientId,Integer dentalStaffId,boolean isDeleted);

    List<User> findAllAccount(Integer doctorId,Integer patientId,Integer dentalStaffId,boolean isDeleted);

    String registerUserAndPatient(PatientAndUserRequest patientAndUserRequest);

    Optional<User> findByPatientId(Integer patientId);

}
