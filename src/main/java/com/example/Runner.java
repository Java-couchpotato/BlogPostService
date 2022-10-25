package com.example;

import com.example.entity.BlogAuthor;
import com.example.entity.RoleName;
import com.example.repository.BlogAuthorRepository;
import com.example.service.BlogUserPasswordService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
@AllArgsConstructor
public class Runner implements CommandLineRunner {
    @Autowired
    private final BlogUserPasswordService passwordService;
    @Autowired
    private final BlogAuthorRepository userRepository;

    public static void main(String[] args) {

    }

    @Override
    public void run(String... args) throws Exception {
        BlogAuthor user = BlogAuthor.builder()
                .firstName("Ivan")
                .lastName("Ivanov")
                .username("ivashka")
                .roleName(RoleName.ADMIN)
                .build();

        userRepository.save(user);
        passwordService.generateAndSavePassword(user, "qwerty");
    }
}
