package com.DuAn.DuAnTotNghiep.service.service;

import com.DuAn.DuAnTotNghiep.entities.User;
import com.DuAn.DuAnTotNghiep.model.request.ChangePasswordRequest;
import com.DuAn.DuAnTotNghiep.model.request.UpdatePasswordRequest;
import com.DuAn.DuAnTotNghiep.model.response.ForgotPasswordResponse;

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
}
