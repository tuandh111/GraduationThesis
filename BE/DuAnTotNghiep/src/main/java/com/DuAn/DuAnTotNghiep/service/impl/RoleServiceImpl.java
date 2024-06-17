package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.Role;
import com.DuAn.DuAnTotNghiep.model.request.RoleRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.repositories.RoleRepositoty;
import com.DuAn.DuAnTotNghiep.service.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepositoty roleRepositoty;

    @Override
    public Role findByRoleName(String roleName) {
        return roleRepositoty.findByRoleName(roleName).orElseThrow(null);
    }

    @Override
    public Role findByRoleId(int roleId) {
        return roleRepositoty.findById(roleId).orElseThrow(null);
    }

    @Override
    public List<Role> findAll() {
        return roleRepositoty.findAll().stream()
                       .filter(role -> !role.isDeleted())
                       .collect(Collectors.toList());
    }

    @Override
    public Role saveRole(RoleRequest roleRequest) {
       var role = Role.builder()
                  .roleName(roleRequest.getRoleName())
                  .description(roleRequest.getDescription()).build();
       roleRepositoty.save(role);
       return role;
    }

    @Override
    public Role updateRole(int roleId, RoleRequest roleRequest) {
        var role = Role
                           .builder()
                           .roleId(roleId)
                           .roleName(roleRequest.getRoleName())
                           .description(roleRequest.getDescription())
                           .build();
        roleRepositoty.save(role);
        return role;
    }

    @Override
    public MessageResponse delete(int roleId) {
        try {
            roleRepositoty.deleteById(roleId);
            return new MessageResponse("successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageResponse("fail");

        }

    }

    @Override
    public MessageResponse sortDeleteRole(int roleId) {
        try {
            var role = Role
                               .builder()
                               .roleId(roleId)
                               .isDeleted(true)
                               .build();
            roleRepositoty.save(role);
            return new MessageResponse("successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageResponse("fail");

        }
    }
}
