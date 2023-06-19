package com.example.repository;

import com.example.entity.BlogAuthor;
import com.example.entity.BlogPost;
import com.example.entity.types.PostStatus;
import liquibase.repackaged.com.opencsv.bean.CsvToBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<BlogPost, UUID> {

    List<BlogPost> findAllByAuthor_UsernameAndStatus(String name, PostStatus published);

    List<BlogPost>findAllByStatus(PostStatus status);

    List<BlogPost> findAllByAuthor_Username(String username);

    BlogPost findBlogPostByIdAndStatus(UUID id,PostStatus status);

  //  List<BlogPost> findAllByAuthorIdOrderByCreatedOnDesc(UUID id);

   // Integer countArticleByAuthor(UUID id);

    @Override
    BlogPost getById(UUID aLong);

    List<BlogPost> findByStatus(PostStatus published);

  //  List<BlogPost> findByStatusOrderByCreatedAtDesc(PostStatus published);

    List<BlogPost> findByAuthorAndStatusOrderByCreatedOnDesc(BlogAuthor author, PostStatus published);

   // CsvToBean<Object> findByAuthorAndStatus(BlogAuthor author, PostStatus published);
}
