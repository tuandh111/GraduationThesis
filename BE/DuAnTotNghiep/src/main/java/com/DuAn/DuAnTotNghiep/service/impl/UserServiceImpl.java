package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.User;
import com.DuAn.DuAnTotNghiep.exception.NotFoundException;
import com.DuAn.DuAnTotNghiep.model.request.ChangePasswordRequest;
import com.DuAn.DuAnTotNghiep.model.request.UpdatePasswordRequest;
import com.DuAn.DuAnTotNghiep.repositories.UserRepository;
import com.DuAn.DuAnTotNghiep.security.service.JwtService;
import com.DuAn.DuAnTotNghiep.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtService jwtService;

    //Tất cả các logic xử lí điều phải xử lí trong service này hết
    @Override
    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {
        User user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        // check if the current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        // check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Password are not the same");
        }

        // update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // save the new password
        userRepository.save(user);
    }

    @Override
    public User updateUser(User request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new NotFoundException("Not Found User"));
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setGender(request.getGender());
        user.setBirthDay(request.getBirthDay());
        userRepository.save(user);
        return user;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

//    @Override
//    public ForgotPasswordResponse forgotPassword(String email) {
//        User user = userRepository.findByEmail(email).orElseThrow(null);
//        ForgotPasswordResponse forgotPasswordResponse = new ForgotPasswordResponse();
//        String verify = ConfigVNPay.getRandomString(6);
//        if (user != null) {
//            try {
//                mailerService.sendVerify(new MailInfo(email, "Chao mung ban den voi Twobee", "Đây là mã xác nhận của bạn: " + verify));
//                forgotPasswordResponse.setMessage("success");
//                forgotPasswordResponse.setVerify(verify);
//                System.out.println("run email successfully");
//            } catch (MessagingException e) {
//                forgotPasswordResponse.setMessage("success");
//            }
//        }else{
//            forgotPasswordResponse.setMessage("errorEmail");
//        }
//        return forgotPasswordResponse;
//    }

    @Override
    public void updatePassword(UpdatePasswordRequest updatePasswordRequest) {
        User user = userRepository.findByEmail(updatePasswordRequest.getEmail()).orElseThrow(null);
        user.setPassword(passwordEncoder.encode(updatePasswordRequest.getPassword()));
        userRepository.save(user);
    }
}
