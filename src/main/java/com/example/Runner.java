package com.example;

import com.example.entity.role.Permission;
import com.example.entity.types.AccountStatus;
import com.example.entity.BlogAuthor;
import com.example.entity.role.Role;
import com.example.repository.AuthorRepository;
import com.example.service.BlogUserPasswordService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
@Transactional
@AllArgsConstructor
public class Runner implements CommandLineRunner {
    @Autowired
    private final BlogUserPasswordService passwordService;
    @Autowired
    private final AuthorRepository userRepository;

    public static void main(String[] args) {

    }

    @Override
    public void run(String... args) throws Exception {

        Permission permissionList=Permission.ADMIN_SEE_ADMIN_ROLES;
        BlogAuthor user = BlogAuthor.builder()
                .firstName("Ivan")
                .lastName("Ivanov")
                .username("ivashka")
                //.role(Role.ADMIN, List<permissionList>)
                .isAdmin(false)
                .accountStatus(AccountStatus.ACTIVE)
                .createdOn(Instant.now())
                .build();

        userRepository.save(user);
        passwordService.generateAndSavePassword(user, "qwerty");
    }
}
