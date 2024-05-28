package com.DuAn.DuAnTotNghiep.service.service;

import com.DuAn.DuAnTotNghiep.entities.Role;

public interface RoleService {
    Role findByRoleName(String name);
    Role findByRoleId(int roleId);
}
