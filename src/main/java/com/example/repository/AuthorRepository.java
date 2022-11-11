package com.example.repository;

import com.example.entity.BlogAuthor;
import com.example.entity.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<BlogAuthor,Long> {

    @Override
    Optional<BlogAuthor> findById(Long id);

    BlogAuthor findByUsername(String username);

    boolean existsByUsername(String authorUsername);

    boolean findAllByRole(Role equals);
}
