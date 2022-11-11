package com.example.controller;

import com.example.dto.request.AdminRequestDto;
import com.example.dto.request.EntryPasswordUpdateDTO;
import com.example.dto.response.RegularAuthorsResponseDTO;
import com.example.dto.response.AuthorRoleResponseDTO;
import com.example.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {


    private final AdminService adminService;

    private final EntryController entryService;


    @PutMapping("users/{userId}/promote")
    public void promote(@PathVariable Long userId) {
        adminService.promoteToAdmin(userId);
    }

    @PutMapping("users/{userId}/demote")
    public void demote(@PathVariable Long userId) {
        adminService.demoteToUser();
    }

    @PreAuthorize("hasRole('ADMIN')|| hasAuthority('admin.posts.rw')")
    @PatchMapping("users/{userId}")
    public void updateUsersData(AdminRequestDto adminRequestDto, @PathVariable String userId) {
        adminService.udateUsersNameOrLastname();
    }

    @PutMapping("users/{userId}/password?reset-session=true")
    public void changePassword(@AuthenticationPrincipal EntryPasswordUpdateDTO passwordUpdateDTO) {
        entryService.setNewPassword(passwordUpdateDTO);
    }

    @GetMapping("/users?admin=true")
    public List<RegularAuthorsResponseDTO> showRegularUsers() {
        return adminService.showsRegularUsersToAdmin();
    }

    @GetMapping("users/{userId}/roles")
    public void showUsersRoles(@PathVariable String userId) {
        adminService.showRoles();
    }

    @PutMapping("users/{userId}/roles?reset-session=true")
    public AuthorRoleResponseDTO grantRole() {
        return adminService.grantUserRole();
    }

    @DeleteMapping("users/{userId}/roles?reset-session=true")
    public AuthorRoleResponseDTO revokeRole() {
        return adminService.revokeUserRole();
    }
    @GetMapping("/roles")
    public void showRoles(List<AuthorRoleResponseDTO>roleResponseDTO){
        adminService.showAllRolesAvailable();
    }

}
