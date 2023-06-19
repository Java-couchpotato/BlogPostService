package com.example.repository;

import com.example.entity.BlogAuthor;
import com.example.entity.BlogAuthorPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogUserPasswordRepository extends JpaRepository<BlogAuthorPassword,Long> {

    BlogAuthorPassword findByUser(BlogAuthor blogAuthor);
}
