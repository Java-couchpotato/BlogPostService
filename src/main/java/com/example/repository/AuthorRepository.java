package com.example.repository;

import com.example.entity.BlogAuthor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface AuthorRepository extends JpaRepository<BlogAuthor,Long> {

    Optional<BlogAuthor> findById(UUID id);

    @Override
    List<BlogAuthor> findAll(Sort sort);

    BlogAuthor findByUsername(String username);

    boolean existsByUsername(String authorUsername);

}
