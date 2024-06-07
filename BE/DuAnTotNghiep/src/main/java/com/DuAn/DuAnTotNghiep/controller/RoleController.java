package com.DuAn.DuAnTotNghiep.controller;

import com.DuAn.DuAnTotNghiep.entities.Role;
import com.DuAn.DuAnTotNghiep.model.request.RoleRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;
import com.DuAn.DuAnTotNghiep.service.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Validated
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping("list-role")
    @Operation(summary = "List Role")
    public ResponseEntity<List<Role>> getAllRole() {
        return ResponseEntity.ok(roleService.findAll());
    }

    @GetMapping("role-id/{Id}")
    @Operation(summary = "RoleId")
    public ResponseEntity<Role> getRoleId( @PathVariable Integer Id) {
        return ResponseEntity.ok(roleService.findByRoleId(Id));
    }
    @PostMapping("save-role")
    @Operation(summary = "Save role")
    public ResponseEntity<Role> saveRole(@Valid  @RequestBody RoleRequest roleRequest){
        return ResponseEntity.ok(roleService.saveRole(roleRequest));
    }
    @PutMapping("update-role/{Id}")
    @Operation(summary = "update role")
    public ResponseEntity<Role> updateRole(@PathVariable int Id, @Valid @RequestBody RoleRequest roleRequest){
        return ResponseEntity.ok(roleService.updateRole(Id, roleRequest));
    }

    @DeleteMapping("delete-role/{Id}")
    @Operation(summary = "delete role")
    public ResponseEntity<MessageResponse> deleteRole(@PathVariable int Id){
        return ResponseEntity.ok(roleService.delete(Id));
    }
}
