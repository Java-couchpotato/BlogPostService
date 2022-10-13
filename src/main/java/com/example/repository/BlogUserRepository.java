package com.example.repository;

import com.example.entity.BlogUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlogUserRepository extends JpaRepository<BlogUser,Long> {

    @Override
    Optional<BlogUser> findById(Long id);
}
