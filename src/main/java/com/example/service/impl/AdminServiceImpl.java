package com.example.service.impl;

import com.example.dto.response.RegularAuthorsResponseDTO;
import com.example.dto.response.AuthorRoleResponseDTO;
import com.example.repository.AuthorRepository;
import com.example.repository.BlogSessionRepository;
import com.example.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminServiceImpl implements AdminService {

    private final AuthorRepository authorRepository;
    private final BlogSessionRepository sessionRepository;

    @Override
    public void promoteToAdmin(Long id) {
        var author = authorRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        author.setAdmin(true);
    }

    @Override
    public void demoteToUser() {

    }

    @Override
    public void udateUsersNameOrLastname() {

    }

    @Override
    public List<RegularAuthorsResponseDTO> showsRegularUsersToAdmin() {
        return null;
    }

    @Override
    public void showRoles() {

    }

    @Override
    public AuthorRoleResponseDTO grantUserRole() {
        return null;
    }

    @Override
    public AuthorRoleResponseDTO revokeUserRole() {
        return null;
    }

    @Override
    public void showAllRolesAvailable() {

    }
}
