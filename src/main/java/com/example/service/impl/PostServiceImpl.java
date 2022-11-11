package com.example.service.impl;

import com.example.dto.request.PostCreateRequestDTO;
import com.example.dto.request.PostSearchRequestDTO;
import com.example.dto.request.PostUpdateRequestDTO;
import com.example.dto.request.UserInfoDto;
import com.example.dto.response.BlogPostCreateResponseDTO;
import com.example.dto.response.BlogPostResponseByIdDTO;
import com.example.dto.response.BlogPostSearchResponseDTO;
import com.example.entity.BlogAuthor;
import com.example.entity.BlogPost;
import com.example.entity.Tag;
import com.example.entity.types.PostStatus;
import com.example.repository.AuthorRepository;
import com.example.repository.PostRepository;
import com.example.repository.TagRepository;
import com.example.service.PostService;
import com.example.utils.BlogConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    private final TagRepository tagRepository;
    // @PersistenceContext // == @Autowired for EntityManager
    @PersistenceContext
    private EntityManager entityManager;


    public PostServiceImpl(PostRepository postRepository, AuthorRepository authorRepository, EntityManager entityManager, TagRepository tagRepository) {
        this.postRepository = postRepository;
        this.authorRepository = authorRepository;
        this.entityManager = entityManager;
        this.tagRepository = tagRepository;
    }

    @Override
    public List<BlogPostSearchResponseDTO> findLatestPosts(String header) {

        List<BlogPost> posts;
        var var = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().toList();

        if (var.toString().contains("admin.posts.pou")
        ) {
            posts = postRepository.findAll();
        }

        else {
            posts = postRepository.findAllByStatus(PostStatus.PUBLISHED);
        }
        return posts.stream()
                .sorted(Comparator.comparing(BlogPost::getCreatedOn).reversed())
                .map(BlogConverter::mapToDto)
                .toList();

    }


    @Override
    public BlogPostCreateResponseDTO create(PostCreateRequestDTO postCreateRequestDTO) {

        BlogAuthor author = authorRepository.findById(postCreateRequestDTO.authorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UPGRADE_REQUIRED,
                        String.format("Blog with ID %d is in status [INACTIVE]", postCreateRequestDTO.authorId())));


        List<Tag> tagsList = postCreateRequestDTO.tagsList().stream()
                .map(this::getTag)
                .toList();

        BlogPost blogPost = BlogPost.builder()
                .title(postCreateRequestDTO.title())
                .body(postCreateRequestDTO.body())
                .tags(tagsList)
                .status(PostStatus.PUBLISHED)
                .author(author)
                .createdOn(Instant.from(LocalDateTime.now()))
                .updatedOn(Instant.from(LocalDateTime.now())).build();

        return BlogConverter.mapToDtoCreate((postRepository.save(blogPost)));
    }

    private Tag getTag(String str) {
        Tag tag = tagRepository.findByText(str);
        if (tag == null) {
            tag = Tag.builder()
                    .text(str)
                    .build();
            tag = tagRepository.save(tag);
        }
        return tag;
    }

    @Override
    public List<BlogPostSearchResponseDTO> searchPosts(PostSearchRequestDTO searchRequestDTO) {

        return postRepository.findAllByStatus(PostStatus.PUBLISHED).stream()
                .map(BlogConverter::mapToDto)
                .toList();
    }

    @Override
    public BlogPostResponseByIdDTO findPostById(Long id, UserInfoDto userInfoDto) {

        var post = postRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        var author = userInfoDto.blogAuthor();
        BlogPostResponseByIdDTO postDto = null;
        if (author.equals(post.getAuthor())
                || userInfoDto.permissions().contains("admin.posts.ro")
                || post.getStatus().equals(PostStatus.PUBLISHED)) {
            postDto = BlogConverter.mapToDtoById(post);
        }
        return postDto;
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





