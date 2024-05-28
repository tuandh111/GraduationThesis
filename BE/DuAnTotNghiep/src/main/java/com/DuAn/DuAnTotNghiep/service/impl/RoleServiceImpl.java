package com.DuAn.DuAnTotNghiep.service.impl;

import com.DuAn.DuAnTotNghiep.entities.Role;
import com.DuAn.DuAnTotNghiep.repositories.RoleRepositoty;
import com.DuAn.DuAnTotNghiep.service.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
