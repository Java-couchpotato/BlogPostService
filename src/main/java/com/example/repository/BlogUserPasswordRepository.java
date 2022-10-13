package com.example.repository;

import com.example.entity.BlogUser;
import com.example.entity.BlogUserPassword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogUserPasswordRepository extends JpaRepository<BlogUserPassword,Long> {

    BlogUserPassword findByUser(BlogUser blogUser);
}
