package com.example.service.impl;

import com.example.entity.BlogUser;
import com.example.entity.BlogUserPassword;
import com.example.repository.BlogUserPasswordRepository;
import com.example.repository.BlogUserRepository;
import com.example.service.BlogUserPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class BlogUserPasswordServiceImpl implements BlogUserPasswordService {

    @Autowired
    private BlogUserRepository userRepository;
    @Autowired
    private BlogUserPasswordRepository passwordRepository;

    @Override
    public void generateAndSavePassword(BlogUser blogUser, String password) {
        String salt = BCrypt.gensalt();
        String encriptedPassword = BCrypt.hashpw(password, salt);

        BlogUserPassword blogUserPassword = new BlogUserPassword(
                blogUser,
                encriptedPassword,
                salt
        );
        passwordRepository.save(blogUserPassword);
    }

    @Override
    public BlogUser getMatchedAccount(String username, String password) {
        BlogUser blogUser = userRepository.findByUsername(username);

        if (blogUser == null) {
            return null;
        }
        BlogUserPassword userPassword = passwordRepository.findByUser(blogUser);
        var actualPasswordHash = BCrypt.hashpw(password, userPassword.getSalt());

        return actualPasswordHash.equals(userPassword.getPassword()) ? blogUser : null;

    }
}
