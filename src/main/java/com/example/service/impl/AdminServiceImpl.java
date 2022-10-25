package com.example.service.impl;

import com.example.dto.response.RegularAuthorsResponseDTO;
import com.example.dto.response.UserRoleResponseDTO;
import com.example.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminServiceImpl implements AdminService {
//    @Autowired
//    private AdminRepository adminRepository;
    @Override
    public void promoteToAdmin() {

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
    public UserRoleResponseDTO grantUserRole() {
        return null;
    }

    @Override
    public UserRoleResponseDTO revokeUserRole() {
        return null;
    }

    @Override
    public void showAllRolesAvailable() {

    }
}
