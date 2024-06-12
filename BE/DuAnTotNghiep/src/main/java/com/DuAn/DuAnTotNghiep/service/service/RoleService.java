package com.DuAn.DuAnTotNghiep.service.service;

import com.DuAn.DuAnTotNghiep.entities.Role;
import com.DuAn.DuAnTotNghiep.model.request.RoleRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;

import java.util.List;

public interface RoleService {
    Role findByRoleName(String name);
    Role findByRoleId(int roleId);

    List<Role> findAll();

    Role saveRole(RoleRequest roleRequest);

    Role updateRole(int roleId, RoleRequest roleRequest);

    MessageResponse delete(int roleId);

    MessageResponse sortDeleteRole(int roleId);

}
