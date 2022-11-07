package com.example.service.impl;

import com.example.dto.request.PostCreateRequestDTO;
import com.example.dto.request.PostSearchRequestDTO;
import com.example.dto.request.PostUpdateRequestDTO;
import com.example.dto.response.BlogPostCreateResponseDTO;
import com.example.dto.response.BlogPostResponseByIdDTO;
import com.example.dto.response.BlogPostSearchResponseDTO;
import com.example.entity.BlogAuthor;
import com.example.entity.BlogPost;
import com.example.entity.role.Role;
import com.example.entity.types.AccountStatus;
import com.example.entity.types.PostStatus;
import com.example.repository.AuthorRepository;
import com.example.repository.PostRepository;
import com.example.service.PostService;
import com.example.utils.BlogConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Transactional
@Slf4j
@Service

public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final AuthorRepository authorRepository;

    public PostServiceImpl(PostRepository postRepository, AuthorRepository authorRepository) {
        this.postRepository = postRepository;
        this.authorRepository = authorRepository;
    }


    @Override
    public List<BlogPostSearchResponseDTO> findLatestPosts() {

        var permission = authorRepository.findAllByRole(Role.ADMIN.getPermissions().toString().equals("admin.posts.ro"));

        if (permission) {
            return postRepository.findAll().stream()
                    .sorted(Comparator.comparing(BlogPost::getCreatedOn).reversed())
                    .map(BlogConverter::mapToDto)
                    .toList();
        } else {
            return postRepository.findAllByStatus(PostStatus.PUBLISHED).stream()
                    .sorted(Comparator.comparing(BlogPost::getCreatedOn).reversed())
                    .map(BlogConverter::mapToDto)
                    .toList();
        }
    }

    @Override
    public BlogPostCreateResponseDTO create(PostCreateRequestDTO postCreateRequestDTO) {

        BlogAuthor author = authorRepository.findById(postCreateRequestDTO.authorId())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.UPGRADE_REQUIRED,
                   String.format("Blog with ID %d is in status [INACTIVE]", postCreateRequestDTO.authorId())));

//        if (author.getAccountStatus() == AccountStatus.INACTIVE) {
//            throw new ResponseStatusException(HttpStatus.UPGRADE_REQUIRED,
//                    String.format("Blog with ID %d is in status [INACTIVE]", author.getId()));
//        }

        BlogPost blogPost = BlogPost.builder()
                .title(postCreateRequestDTO.title())
                .body(postCreateRequestDTO.body())
                //.tags(postCreateRequestDTO.tags)
                .status(PostStatus.PUBLISHED)
                .author(author)
                .createdOn(Instant.from(LocalDateTime.now()))
                .updatedOn(Instant.from(LocalDateTime.now())).build();

        return BlogConverter.mapToDtoCreate((postRepository.save(blogPost)));
    }
    // @PersistenceContext // == @Autowired for EntityManager
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<BlogPostSearchResponseDTO> searchPosts(PostSearchRequestDTO searchRequestDTO) {

        var permission = authorRepository.findAllByRole(Role.ADMIN.getPermissions().toString().equals("admin.posts.ro"));
        if (permission) {
            return postRepository.findAll().stream()
                    .map(BlogConverter::mapToDto)
                    .toList();
        } else {
            return postRepository.findAllByStatus(PostStatus.PUBLISHED).stream()
                    .map(BlogConverter::mapToDto)
                    .toList();
        }
    }

    @Override
    public BlogPostResponseByIdDTO findPostById(Long id) {
        BlogPost post = postRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return BlogConverter.mapToDtoById(post);
    }


    @Override
    public void update(PostUpdateRequestDTO updateRequestDTO, Long postId) {

        BlogPost blogPost = postRepository.findById(postId).orElseThrow();

        blogPost.setTitle(updateRequestDTO.title());
        blogPost.setBody(updateRequestDTO.body());
        //blogPost.setTags(updateRequestDTO.tags);

        postRepository.save(blogPost);
    }

    @Override
    public void setStatusBlocked(Long id) {

        BlogPost post = postRepository.findById(id).orElseThrow();

        if (!post.getStatus().equals(PostStatus.BLOCKED)) {
            post.setStatus(PostStatus.BLOCKED);
        }

    }

    @Override
    public void setStatusUnpublished(Long id) {

        BlogPost post = postRepository.findById(id).orElseThrow();

        if (!post.getStatus().equals(PostStatus.UNPUBLISHED)) {
            post.setStatus(PostStatus.UNPUBLISHED);
        }

    }

    @Override
    public void setStatusPublished(Long id) {

        BlogPost post = postRepository.findById(id).orElseThrow();

        if (!post.getStatus().equals(PostStatus.PUBLISHED)) {
            post.setStatus(PostStatus.PUBLISHED);
        }

    }

    @Override
    public List<BlogPostSearchResponseDTO> showArticlesByUserName(String name) {

        return postRepository.findAllByAuthor_Username(name)
                .stream()
                .map(BlogConverter::mapToDtoSearch)
                .toList();
    }

    @Override
    public void deleteById(long postId) {

        postRepository.deleteById(postId);
        log.info("Post with ID = {} deleted", postId);
    }


}





