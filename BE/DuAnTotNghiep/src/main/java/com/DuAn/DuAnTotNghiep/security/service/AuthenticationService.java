package com.DuAn.DuAnTotNghiep.security.service;


import com.DuAn.DuAnTotNghiep.entities.*;
import com.DuAn.DuAnTotNghiep.model.request.RegisterRequest;
import com.DuAn.DuAnTotNghiep.repositories.*;
import com.DuAn.DuAnTotNghiep.service.service.RoleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.DuAn.DuAnTotNghiep.entities._enum.TokenType;
import com.DuAn.DuAnTotNghiep.model.request.AuthenticationRequest;
import com.DuAn.DuAnTotNghiep.model.response.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final RoleService roleService;
  private final UserRepository userRepository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  @Autowired
  DoctorRepository doctorRepository;
  @Autowired
  DentalStaffRepository dentalStaffRepository;
  @Autowired
  PatientRepository patientRepository;

  public AuthenticationResponse register(RegisterRequest request) {
    Role role = roleService.findByRoleId(request.getRoleId());
    Doctor doctor = doctorRepository.findById(request.getDoctorId()).orElse(null);
    Patient patient =patientRepository.findById(request.getPatientId()).orElse(null);
    DentalStaff dentalStaff = dentalStaffRepository.findById(request.getDentalStaffId()).orElse(null);
    if(role == null){
      return null;
    }
    var user = User.builder()
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(role)
            .doctor(doctor)
            .dentalStaff(dentalStaff)
            .patient(patient)
            .build();
    var savedUser = userRepository.save(user);
    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);
    saveUserToken(savedUser, jwtToken);
    return AuthenticationResponse.builder()
            .accessToken(jwtToken)
            .refreshToken(refreshToken)
            .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
            )
    );
    var user = userRepository.findByEmail(request.getEmail())
            .orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
    return AuthenticationResponse.builder()
            .accessToken(jwtToken)
            .refreshToken(refreshToken)
            .user(user)
            .build();
  }

  private void saveUserToken(User user, String jwtToken) {
    var token = Token.builder()
            .user(user)
            .token(jwtToken)
            .tokenType(TokenType.BEARER)
            .expired(false)
            .revoked(false)
            .build();
    tokenRepository.save(token);
  }


  private void revokeAllUserTokens(User user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }

  public void refreshToken(
          HttpServletRequest request,
          HttpServletResponse response
  ) throws IOException {
    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    final String refreshToken;
    final String userEmail;
    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
      return;
    }
    refreshToken = authHeader.substring(7);
    userEmail = jwtService.extractUsername(refreshToken);
    if (userEmail != null) {
      var user = this.userRepository.findByEmail(userEmail)
              .orElseThrow();
      if (jwtService.isTokenValid(refreshToken, user)) {
        var accessToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);
        var authResponse = AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
        new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
      }
    }
  }

  public boolean validPassword(Map<String, String> passwordMap){
    String rawPassword=passwordMap.get("newPassword");
    String encodedPassword=passwordMap.get("currentPassword");
    return passwordEncoder.matches(rawPassword,encodedPassword);
  }

}
