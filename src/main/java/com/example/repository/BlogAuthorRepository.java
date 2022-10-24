package com.example.repository;

import com.example.entity.BlogAuthor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlogAuthorRepository extends JpaRepository<BlogAuthor,Long> {

    @Override
    Optional<BlogAuthor> findById(Long id);

    BlogAuthor findByUsername(String username);

    boolean existsByUsername(String authorUsername);
}
