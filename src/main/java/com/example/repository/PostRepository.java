package com.example.repository;

import com.example.entity.BlogPost;
import com.example.entity.role.Role;
import com.example.entity.types.PostStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<BlogPost, Long> {

    List<BlogPost> findAllByStatus(PostStatus published);

    List<BlogPost> findAllByAuthor_Username(String username);

    @Override
    Optional<BlogPost> findById(Long aLong);

    Integer countArticleByAuthor(Long id);

   List<BlogPost> findByAuthorRole(Role role);



}
