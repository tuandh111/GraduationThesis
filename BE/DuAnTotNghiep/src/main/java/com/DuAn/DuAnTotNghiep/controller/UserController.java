package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.entities.User;
import com.DuAn.DuAnTotNghiep.model.request.ChangePasswordRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.service.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    UserService userService;

    @PatchMapping
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request, Principal connectedUser) {
        userService.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }
    @GetMapping("login")
    public String login(){
        return "index";
    }

    @GetMapping("/check-exist-email")
    @Operation(summary = "Get user by email")
    public ResponseEntity<?> checkExistEmail(@RequestParam("email") String email){
        Optional<User> optionalUser = userService.findByEmail(email);
        return ResponseEntity.ok(new MessageResponse(optionalUser.isPresent() ? "true" : "false"));
    }

    @GetMapping("/check-exist-user-by-object")
    @Operation(summary = "Check exist user")
    public ResponseEntity<MessageResponse> checkUserByAnObject(@RequestParam(value = "doctorId",required = false) Integer doctorId,
                                                               @RequestParam(value = "patientId",required = false) Integer patientId,
                                                               @RequestParam(value = "dentalStaffId",required = false) Integer dentalStaffId){
        boolean isExist = userService.checkUserByAnObject(doctorId,patientId,dentalStaffId);
        return ResponseEntity.ok(new MessageResponse(isExist ? "true" : "false"));
    }

}