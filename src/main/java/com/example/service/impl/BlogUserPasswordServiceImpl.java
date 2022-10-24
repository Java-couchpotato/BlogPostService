package com.example.service.impl;

import com.example.entity.BlogAuthor;
import com.example.entity.BlogUserPassword;
import com.example.repository.BlogUserPasswordRepository;
import com.example.repository.BlogAuthorRepository;
import com.example.service.BlogUserPasswordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Slf4j
@Service
public class BlogUserPasswordServiceImpl implements BlogUserPasswordService {

    @Autowired
    private BlogAuthorRepository userRepository;
    @Autowired
    private BlogUserPasswordRepository passwordRepository;

    @Override
    public void generateAndSavePassword(BlogAuthor blogAuthor, String password) {
        String salt = BCrypt.gensalt();
        String encriptedPassword = BCrypt.hashpw(password, salt);

        BlogUserPassword blogUserPassword = new BlogUserPassword(
                blogAuthor,
                encriptedPassword,
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
