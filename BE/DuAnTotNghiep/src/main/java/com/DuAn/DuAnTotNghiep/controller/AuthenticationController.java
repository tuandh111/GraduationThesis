package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.Rest.RestFB;
import com.DuAn.DuAnTotNghiep.Rest.RestGG;
import com.DuAn.DuAnTotNghiep.entities.Patient;
import com.DuAn.DuAnTotNghiep.entities.User;
import com.DuAn.DuAnTotNghiep.entities._enum.Role;
import com.DuAn.DuAnTotNghiep.exception.BadRequestException;
import com.DuAn.DuAnTotNghiep.model.UserFacebookDto;
import com.DuAn.DuAnTotNghiep.model.UserGoogleDto;
import com.DuAn.DuAnTotNghiep.model.request.*;
import com.DuAn.DuAnTotNghiep.model.response.AuthenticationResponse;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.security.service.AuthenticationService;
import com.DuAn.DuAnTotNghiep.security.service.GetTokenRefreshToken;
import com.DuAn.DuAnTotNghiep.security.service.JwtService;
import com.DuAn.DuAnTotNghiep.service.service.PatientService;
import com.DuAn.DuAnTotNghiep.service.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

// URL SWARGGER:    http://localhost:8081/swagger-ui/index.html#
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Validated
public class AuthenticationController {
    @Autowired
    UserService userService;
    @Autowired
    PatientService patientService;
    private final JwtService jwtService;

    private final AuthenticationService service;

    @PostMapping("/register")
    @Operation(summary = "Đăng kí")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        Map<String, Object> jsonError = new HashMap<>();
        if (checkEmail(request.getEmail())) {
            jsonError.put("message", "ErrorEmail");
            return ResponseEntity.ok(jsonError);
        }
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    @Operation(summary = "Đăng nhập")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request, HttpServletRequest httpServletRequest) {
//        GetTokenRefreshToken.getToken(httpServletRequest);
//        System.out.println("Email: " + jwtService.extractUsername("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0dWFuZGhwYzA1MDc2QGZwdC5lZHUudm4iLCJpYXQiOjE3MTA4MDk5NTIsImV4cCI6MTcxMDg5NjM1Mn0.8Ata74reIX-DVJavfDNwaeHsSehS5A2SxX3KDjGNcAY"));
        User user = userService.findByEmail(request.getEmail()).orElse(null) ;
        if (user.isDeleted()) {
            return ResponseEntity.status(HttpStatus.GONE).body(null); // User is deleted
        }
        return ResponseEntity.ok(service.authenticate(request));
    }

    @GetMapping("user-login")
    @Operation(summary = "login")
    public ResponseEntity<User> userResponseEntity(HttpServletRequest httpServletRequest) {
        String token = GetTokenRefreshToken.getToken(httpServletRequest);
        String email = jwtService.extractUsername(token);
        return ResponseEntity.ok(userService.findByEmail(email).orElseThrow(null));
    }


    @GetMapping("/get-user-by-token")
    @Operation(summary = "Get user by token")
    public ResponseEntity<?> getUser(HttpServletRequest httpServletRequest) {
        var token = GetTokenRefreshToken.getToken(httpServletRequest);
        var email = jwtService.extractUsername(token);
        Optional<User> optionalUser = userService.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        //User user = userService.findByEmail(email).get();
        //return ResponseEntity.ok(user);
    }

    @GetMapping("/get-user-by-email")
    @Operation(summary = "Get user by email")
    public ResponseEntity<?> getUserByEmail(@RequestParam("email") String email) {
        Optional<User> optionalUser = userService.findByEmail(email);

        if (optionalUser.isPresent()) {
            return ResponseEntity.ok(optionalUser.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
//        return ResponseEntity.ok(userService.findByEmail(email).get());
    }


    @PostMapping("/refresh-token")
    @Operation(summary = "Refresh token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        service.refreshToken(request, response);
    }


    @PostMapping("/update-user")
    @Operation(summary = "Cập nhật user")
    public ResponseEntity<?> updateProfile(@RequestBody User request) {
        return ResponseEntity.ok(userService.updateUser(request));
    }

    @PostMapping("/disabled-user")
    @Operation(summary = "Cập nhật user")
    public ResponseEntity<?> disabledUser(@RequestBody User request) {
        return ResponseEntity.ok(userService.disabledUser(request));
    }

    @GetMapping("")
    @Operation(summary = "Danh sách người dùng")
    public ResponseEntity<?> findAllUser() {
        System.out.println(userService.findAll());
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping("/update-password")
    @Operation(summary = "Cập nhật mật khẩu")
    public ResponseEntity<?> updatePassword(@RequestBody UpdatePasswordRequest updatePasswordRequest) {
        userService.updatePassword(updatePasswordRequest);
        return ResponseEntity.ok(new MessageResponse("success"));
    }

    public boolean checkEmail(String email) {
        Optional<User> user = userService.findByEmail(email);
        if (user.isPresent()) return true;
        return false;
    }

    @Value("${google.client.id}")
    private String googleClientId;

    @Value("${google.client.secret}")
    private String googleClientSecret;

    @Value("${google.redirect.uri}")
    private String googleRedirectUri;

    @SneakyThrows
    @PostMapping("/google/callback")
    public ResponseEntity<?> googleCallback(@RequestBody Map<String, String> body) {
        String code = body.get("code");
        if (code != null) {
            String accessToken = RestGG.getToken(code);
            UserGoogleDto user = RestGG.getUserInfo(accessToken);
            User checkUser = userService.findByEmail(user.getEmail()).orElse(null);
            AuthenticationResponse authenticationResponse = null;
            if (checkUser == null) {
                PatientRequest patientRequest = new PatientRequest();
                patientRequest.setGender("UNISEX");
                Patient patient = patientService.savePatient(patientRequest);
                RegisterRequest registerRequest = RegisterRequest.builder()
                                                          .patientId(patient.getPatientId())
                                                          .email(user.getEmail())
                                                          .roleId(4)
                                                          .password(user.getEmail() + "123").build();

                authenticationResponse =   service.register(registerRequest);
            }else{
                if(checkUser.isDeleted()) {
                    return ResponseEntity.status(HttpStatus.GONE).body(null);
                }
                authenticationResponse= service.authenticateGoogle(new AuthenticationRequest(user.getEmail(),checkUser.getPassword()));
            }
            User checkUser1 = userService.findByEmail(user.getEmail()).orElse(null);
            authenticationResponse.setUser(checkUser1);
            return ResponseEntity.ok(authenticationResponse);
        }
        return ResponseEntity.ok(null);
    }

    @SneakyThrows
    @PostMapping("/facebook/callback")
    public ResponseEntity<?> faceCallback(@RequestBody Map<String, String> body) {
        String code = body.get("code");
        if (code != null) {
            String accessToken = RestFB.getToken(code);
            UserFacebookDto user = RestFB.getUserInfo(accessToken);
            System.out.println("userfb" + user);
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.ok(null);
    }

    @PostMapping("/valid-password")
    public ResponseEntity<Boolean> validPassword(@RequestBody Map<String, String> passwordMap) {
        boolean isValid = service.validPassword(passwordMap);
        return ResponseEntity.ok(isValid);
    }

    @PostMapping("/patient-user")
    @Operation(summary = "Reigister account patient")
    public ResponseEntity<?> patientAndUser(@RequestBody PatientAndUserRequest patientAndUserRequest) {
        String status = userService.registerUserAndPatient(patientAndUserRequest);
        if ("Email exists".equalsIgnoreCase(status)) {
            return ResponseEntity.badRequest().body(new MessageResponse("email already exists"));
        } else if ("Failed".equalsIgnoreCase(status)) {
            return ResponseEntity.badRequest().body(new MessageResponse("failed to create account"));
        } else {
            return ResponseEntity.ok(new MessageResponse("Created account"));
        }
    }
    private final LogoutHandler logoutHandler;

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("logout");
        logoutHandler.logout(request, response, null);
    }

    @GetMapping("/get-email-by-patient-id")
    @Operation(summary = "Get email by patientId")
    public ResponseEntity<?> getEmailByPatientId(@RequestParam("patientId") Integer patientId) {
        Optional<User> optionalUser = userService.findByPatientId(patientId);

        if (optionalUser.isPresent()) {
            return ResponseEntity.ok(optionalUser.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @GetMapping("/get-email-by-doctor-id")
    @Operation(summary = "Get email by doctorId")
    public ResponseEntity<?> getEmailByDoctorId(@RequestParam("doctorId") Integer doctorId) {
        Optional<User> optionalUser = userService.findByDoctorId(doctorId);

        if (optionalUser.isPresent()) {
            return ResponseEntity.ok(optionalUser.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
}
