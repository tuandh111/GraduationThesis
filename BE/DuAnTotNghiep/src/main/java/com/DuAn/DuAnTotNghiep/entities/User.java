package com.DuAn.DuAnTotNghiep.entities;

import com.DuAn.DuAnTotNghiep.entities._enum.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;


import java.util.Collection;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.Nationalized;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Account")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private boolean isDeleted = false;

    private boolean status = true;

    @Email(message = "Email không hợp lệ")
    @NotNull(message = "Vui lòng nhập email")
    private String email;

    @Column
    @NotBlank(message = "Vui lòng nhập mật khẩu")
    @Size(min = 8, message = "Mật khẩu phải chứa ít nhất 8 ký tự")
    @Pattern(regexp = ".*[a-zA-Z].*", message = "Mật khẩu phải chứa ít nhất một chữ cái")
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "roleId")
    private Role role;

    @OneToOne
    @JoinColumn(name = "dentalStaffId")
    private DentalStaff dentalStaff;

    @OneToOne
    @JoinColumn(name = "patientId")
    private Patient patient;

    @OneToOne
    @JoinColumn(name = "doctorId")
    private Doctor doctor;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getCustomAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + ", email='" + email + '\'' + ", password='" + password + '\'' + ", role=" + role + '}';
    }
}
