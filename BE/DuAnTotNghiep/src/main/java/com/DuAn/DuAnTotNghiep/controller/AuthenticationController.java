package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.entities.User;
import com.DuAn.DuAnTotNghiep.entities._enum.Role;
import com.DuAn.DuAnTotNghiep.model.request.AuthenticationRequest;
import com.DuAn.DuAnTotNghiep.model.request.RegisterRequest;
import com.DuAn.DuAnTotNghiep.model.request.UpdatePasswordRequest;
import com.DuAn.DuAnTotNghiep.model.response.AuthenticationResponse;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.security.service.AuthenticationService;
import com.DuAn.DuAnTotNghiep.security.service.GetTokenRefreshToken;
import com.DuAn.DuAnTotNghiep.security.service.JwtService;
import com.DuAn.DuAnTotNghiep.service.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(service.authenticate(request));
    }

    @GetMapping("/get-user-by-token")
    @Operation(summary = "Get user by token")
    public ResponseEntity<?> getUser(HttpServletRequest httpServletRequest){
        var token= GetTokenRefreshToken.getToken(httpServletRequest);
        var email=jwtService.extractUsername(token);
        User user =userService.findByEmail(email).get();
        return ResponseEntity.ok(user);
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


}
