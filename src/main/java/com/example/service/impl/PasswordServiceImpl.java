package com.example.service.impl;

import com.example.entity.BlogAuthor;
import com.example.entity.BlogAuthorPassword;
import com.example.repository.AuthorRepository;
import com.example.repository.BlogUserPasswordRepository;
import com.example.service.BlogUserPasswordService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional

@Service
@AllArgsConstructor
public class PasswordServiceImpl implements BlogUserPasswordService {

    private final AuthorRepository userRepository;
    private final BlogUserPasswordRepository passwordRepository;



    @Override
    public void generateAndSavePassword(BlogAuthor blogAuthor, String password) {
        String salt = BCrypt.gensalt();
        String encryptedPassword = BCrypt.hashpw(password, salt);

        BlogAuthorPassword blogAuthorPassword = BlogAuthorPassword.builder()
                .user(blogAuthor)
                .password(encryptedPassword)
                .salt(salt)
                .build();
        passwordRepository.save(blogAuthorPassword);
    }

    @Override
    public BlogAuthor getMatchedAccount(String username, String password) {
        var blogAuthor = userRepository.findByUsername(username);

        if (blogAuthor == null) {
            return null;
        }
        BlogAuthorPassword userPassword = passwordRepository.findByUser(blogAuthor);
        var actualPasswordHash = BCrypt.hashpw(password, userPassword.getSalt());

        return actualPasswordHash.equals(userPassword.getPassword()) ? blogAuthor : null;

    }
}
