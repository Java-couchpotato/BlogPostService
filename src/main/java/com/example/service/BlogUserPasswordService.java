package com.example.service;

import com.example.entity.BlogUser;

public interface BlogUserPasswordService {
    void generateAndSavePassword(BlogUser blogUser, String password);

    BlogUser getMatchedAccount(String username, String password);
}
