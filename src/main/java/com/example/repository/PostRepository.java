package com.example.repository;

import com.example.entity.BlogAuthor;
import com.example.entity.BlogPost;
import com.example.entity.PostStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<BlogPost, Long> {

    @Override
    List<BlogPost> findAll();

    @Override
    Optional<BlogPost> findById(Long aLong);


    List<BlogPost> findAllByAuthor(String username);

    Integer countArticleByAuthor(Long id);

    //void findBlogPostsByStatusOrderByCreatedOnDesc(PostStatus status);

}
