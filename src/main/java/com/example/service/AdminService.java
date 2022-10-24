package com.example.service;

import com.example.dto.response.RegularAuthorsResponseDTO;
import com.example.dto.response.UserRoleResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {
    void promoteToAdmin();

    void demoteToUser();

    void udateUsersNameOrLastname();

    List<RegularAuthorsResponseDTO> showsRegularUsersToAdmin();

    void showRoles();

    UserRoleResponseDTO grantUserRole();

    UserRoleResponseDTO revokeUserRole();

    void showAllRolesAvailable();
}
