package com.example.service;

import com.example.dto.adminDTO.AdminShowAuthorsDTO;
import com.example.dto.authorDTO.AuthorUpdateRequestDTO;
import com.example.dto.entryDTO.PasswordDTO;
import com.example.dto.authorDTO.AuthorRoleResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {
    void promoteToAdmin(Long id,String role);

    void demoteToUser(Long id);

    void updateUsersNameOrLastname(AuthorUpdateRequestDTO updateRequestDTO,Long id);

    List<AdminShowAuthorsDTO> showsRegularUsersToAdmin();

    void resetPassword(Long id, Boolean resetSession, PasswordDTO passwordDTO);

    void showRoles();

    AuthorRoleResponseDTO grantUserRole();

    AuthorRoleResponseDTO revokeUserRole();

    void showAllRolesAvailable();
}
