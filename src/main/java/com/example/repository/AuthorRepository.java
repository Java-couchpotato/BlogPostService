package com.example.repository;

import com.example.entity.BlogAuthor;
import com.example.entity.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<BlogAuthor,Long> {

    @Override
    Optional<BlogAuthor> findById(Long id);

    BlogAuthor findByUsername(String username);




    boolean existsByUsername(String authorUsername);

}
