package com.example.repository;

import com.example.entity.BlogSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogSessionRepository extends JpaRepository<BlogSession,Long> {
    BlogSession findBySessionId(String id);
}
