package com.DuAn.DuAnTotNghiep.entities;

import com.DuAn.DuAnTotNghiep.config.CustomGrantedAuthority;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int roleId;

    private boolean isDeleted = false;

    @Column(columnDefinition = "NVARCHAR(255)")
    @NotNull
    private String roleName;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<User> users;

    public List<CustomGrantedAuthority> getCustomAuthorities() {
        List<CustomGrantedAuthority> authorities = new ArrayList<>();

        // Tạo CustomGrantedAuthority từ roleName của Role
        authorities.add(new CustomGrantedAuthority("ROLE_" + this.roleName));

        // Tạo CustomGrantedAuthority từ các quyền của Role (nếu có)
        // Ví dụ, tạo từ danh sách permissions của Role
        // Ví dụ:
        // for (Permission permission : permissions) {
        //            authorities.add(new CustomGrantedAuthority(permission.getPermission()));
        //       }
        return authorities;
    }

    @Override
    public String toString() {
        return "Role{" + "roleId=" + roleId + ", roleName='" + roleName + '\'' + ", description='" + description + '\'' + '}';
    }
}
