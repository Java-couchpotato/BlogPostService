package com.example.repository;

import com.example.entity.BlogAuthor;
import com.example.entity.BlogAuthorPassword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogUserPasswordRepository extends JpaRepository<BlogAuthorPassword,Long> {

    BlogAuthorPassword findByUser(BlogAuthor blogAuthor);
}
