package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.Patient;
import com.DuAn.DuAnTotNghiep.entities.User;
import com.DuAn.DuAnTotNghiep.entities._enum.Gender;
import com.DuAn.DuAnTotNghiep.exception.NotFoundException;
import com.DuAn.DuAnTotNghiep.model.request.ChangePasswordRequest;
import com.DuAn.DuAnTotNghiep.model.request.PatientAndUserRequest;
import com.DuAn.DuAnTotNghiep.model.request.PatientRequest;
import com.DuAn.DuAnTotNghiep.model.request.UpdatePasswordRequest;
import com.DuAn.DuAnTotNghiep.repositories.PatientRepository;
import com.DuAn.DuAnTotNghiep.repositories.RoleRepositoty;
import com.DuAn.DuAnTotNghiep.repositories.UserRepository;
import com.DuAn.DuAnTotNghiep.security.service.JwtService;
import com.DuAn.DuAnTotNghiep.service.service.PDFGeneratorService;
import com.DuAn.DuAnTotNghiep.service.service.PatientService;
import com.DuAn.DuAnTotNghiep.service.service.UserService;
import com.DuAn.DuAnTotNghiep.service.service.utils.MailerService;
import com.DuAn.DuAnTotNghiep.utils.MultipartFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.Date;
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

    @Autowired
    PatientRepository patientRepository;
    @Autowired
    RoleRepositoty roleRepositoty;
    @Autowired
    MailerService mailerService;
    @Autowired
    private PDFGeneratorService pdfGeneratorService;


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
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return user;
    }

    @Override
    public User disabledUser(User request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new NotFoundException("Not Found User"));
        user.setDeleted(request.isDeleted());
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



    @Override
    public boolean checkUserByAnObject(Integer doctorId, Integer patientId, Integer dentalStaffId,boolean isDeleted) {
        List<User> users = userRepository.getUserByObject(doctorId,patientId,dentalStaffId,isDeleted);
        return !users.isEmpty();
    }

    @Override
    public List<User> findAllAccount(Integer doctorId, Integer patientId, Integer dentalStaffId, boolean isDeleted) {
        return userRepository.getUserByObject(doctorId,patientId,dentalStaffId,isDeleted);
    }

    @Override
    public String registerUserAndPatient(PatientAndUserRequest patientAndUserRequest) {
        try {
            User checkUser = userRepository.findByEmail(patientAndUserRequest.getEmail()).orElse(null);
            if(checkUser != null){
                return "Email exists";
            }
            Patient patient = Patient.builder()
                                      .fullName(patientAndUserRequest.getFullName())
                                      .phoneNumber(patientAndUserRequest.getPhoneNumber())
                                      .birthday(patientAndUserRequest.getBirthday())
                                      .gender(Gender.valueOf(patientAndUserRequest.getGender()))
                                      .address(patientAndUserRequest.getAddress())
                                      .build();
            Patient patient1= patientRepository.save(patient);
            User user = User.builder()
                                .role(roleRepositoty.findByRoleName("BENH_NHAN").orElse(null))
                                .email(patientAndUserRequest.getEmail())
                                .password(passwordEncoder.encode(patientAndUserRequest.getPassword()))
                                .patient(patient1)
                                .doctor(null)
                                .dentalStaff(null)
                                .build();
            userRepository.save(user);
            if(user.getEmail() != null){
//                byte[] fileBytes = pdfGeneratorService.read("files", "tooth.jpg");
//                MultipartFile file = new MockMultipartFile("tooth.jpg", "tooth.jpg", "image/jpeg", fileBytes);
                mailerService.send(user.getEmail(),"Thông báo đăng kí tài khoản ","Chúc mừng bạn đã tạo thành công tài khoản tại phòng khám nha khoa Tooth Teeth. \nThông tin đăng nhập của bạn là Email: , Password", null);
            }
            return "Successfully";
        }catch (Exception e){
            e.printStackTrace();
            return "Failed";
        }

    }

    @Override
    public Optional<User> findByPatientId(Integer patientId) {
        return userRepository.findByPatient_PatientId(patientId);
    }
}
