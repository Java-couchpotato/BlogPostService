package com.example.repository;

import com.example.entity.BlogAuthor;
import com.example.entity.BlogPost;
import com.example.entity.types.PostStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<BlogPost, Long> {

    List<BlogPost> findAllByAuthor_UsernameAndStatus(String name, PostStatus published);

    List<BlogPost>findAllByStatus(PostStatus status);

    List<BlogPost> findAllByAuthor_Username(String username);

    BlogPost findBlogPostByIdAndStatus(Long id,PostStatus status);

    List<BlogPost> findAllByAuthorIdOrderByCreatedOnDesc(Long id);

    Integer countArticleByAuthor(Long id);

    @Override
    BlogPost getById(Long aLong);
}
