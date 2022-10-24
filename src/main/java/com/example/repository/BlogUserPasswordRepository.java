package com.example.repository;

import com.example.entity.BlogAuthor;
import com.example.entity.BlogUserPassword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogUserPasswordRepository extends JpaRepository<BlogUserPassword,Long> {

    BlogUserPassword findByUser(BlogAuthor blogAuthor);
}
