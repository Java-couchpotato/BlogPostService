package com.example.repository;

import com.example.entity.BlogAuthor;
import com.example.entity.BlogAuthorSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogSessionRepository extends JpaRepository<BlogAuthorSession, Long> {

    BlogAuthorSession findBySessionId(String id);

   List<BlogAuthorSession> findAllByBlogAuthor(BlogAuthor author);

    @Override
    void delete(BlogAuthorSession entity);
}
