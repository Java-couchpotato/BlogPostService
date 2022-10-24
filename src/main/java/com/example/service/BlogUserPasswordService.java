package com.example.service;

import com.example.entity.BlogAuthor;

public interface BlogUserPasswordService {
    void generateAndSavePassword(BlogAuthor blogAuthor, String password);

    BlogAuthor getMatchedAccount(String username, String password);
}
