package com.example.service;

import com.example.dto.response.RegularAuthorsResponseDTO;
import com.example.dto.response.AuthorRoleResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {
    void promoteToAdmin(Long id);

    void demoteToUser();

    void udateUsersNameOrLastname();

    List<RegularAuthorsResponseDTO> showsRegularUsersToAdmin();

    void showRoles();

    AuthorRoleResponseDTO grantUserRole();

    AuthorRoleResponseDTO revokeUserRole();

    void showAllRolesAvailable();
}
