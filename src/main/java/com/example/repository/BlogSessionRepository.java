package com.example.repository;

import com.example.entity.BlogAuthorSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogSessionRepository extends JpaRepository<BlogAuthorSession,Long> {
    BlogAuthorSession findBySessionId(String id);

    void delete(BlogAuthorSession blogAuthorSession);
}
