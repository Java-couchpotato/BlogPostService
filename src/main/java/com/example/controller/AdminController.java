package com.example.controller;

import com.example.dto.adminDTO.AdminShowAuthorsDTO;
import com.example.dto.authorDTO.AuthorUpdateRequestDTO;
import com.example.dto.entryDTO.PasswordDTO;
import com.example.dto.authorDTO.AuthorRoleResponseDTO;
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
    @PreAuthorize("hasRole('ADMIN')&& hasAuthority('admin.admins.rw')")
    public void promote(@PathVariable Long userId,String role) {
        adminService.promoteToAdmin(userId,role);
    }

    @PreAuthorize("hasRole('ADMIN')&& hasAuthority('admin.admins.rw')")
    @PutMapping("users/{userId}/demote")
    public void demote(@PathVariable Long userId) {
        adminService.demoteToUser(userId);
    }

    @PreAuthorize("hasRole('ADMIN')&& hasAuthority('admin.users.rw')")
    @PatchMapping("users/{id}")
    public void updateUsersData(@RequestBody AuthorUpdateRequestDTO adminRequestDto, @PathVariable Long id) {
        adminService.updateUsersNameOrLastname(adminRequestDto, id);
    }

    //
    @PutMapping("users/{userId}/password?reset-session=true")
//    @RequestBody PasswordResetRequestDTO request,

    public void changePassword(@AuthenticationPrincipal PasswordDTO passwordDTO,
                               @PathVariable Long userId,
                               @RequestParam(value = "reset-session", required = false) Boolean resetSession) {
        adminService.resetPassword(userId,resetSession,passwordDTO);
    }

    @GetMapping("/users?admin=true")
    public List<AdminShowAuthorsDTO> showRegularUsers() {
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
    public void showRoles(List<AuthorRoleResponseDTO> roleResponseDTO) {
        adminService.showAllRolesAvailable();
    }

}
