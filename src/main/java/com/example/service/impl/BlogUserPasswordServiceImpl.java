package com.example.service.impl;

import com.example.entity.BlogAuthor;
import com.example.entity.BlogUserPassword;
import com.example.repository.BlogUserPasswordRepository;
import com.example.repository.BlogAuthorRepository;
import com.example.service.BlogUserPasswordService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Slf4j
@Service
@NoArgsConstructor
public class BlogUserPasswordServiceImpl implements BlogUserPasswordService {

    private BlogAuthorRepository userRepository;
    private BlogUserPasswordRepository passwordRepository;

    @Autowired
    public BlogUserPasswordServiceImpl(BlogAuthorRepository userRepository, BlogUserPasswordRepository passwordRepository) {
        this.userRepository = userRepository;
        this.passwordRepository = passwordRepository;
    }

    @Override
    public void generateAndSavePassword(BlogAuthor blogAuthor, String password) {
        String salt = BCrypt.gensalt();
        String encryptedPassword = BCrypt.hashpw(password, salt);

        BlogUserPassword blogUserPassword = new BlogUserPassword(
                blogAuthor,
                encryptedPassword,
                salt
        );
        passwordRepository.save(blogUserPassword);
    }

    @Override
    public BlogAuthor getMatchedAccount(String username, String password) {
        BlogAuthor blogAuthor = userRepository.findByUsername(username);

        if (blogAuthor == null) {
            return null;
        }
        BlogUserPassword userPassword = passwordRepository.findByUser(blogAuthor);
        var actualPasswordHash = BCrypt.hashpw(password, userPassword.getSalt());

        return actualPasswordHash.equals(userPassword.getPassword()) ? blogAuthor : null;

    }
}
