package com.example.repository;

import com.example.entity.BlogPost;
import com.example.entity.types.PostStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<BlogPost, Long> {

    List<BlogPost> findAllByStatus(PostStatus published);

    List<BlogPost> findAllByAuthor_Username(String username);

    Integer countArticleByAuthor(Long id);


}
