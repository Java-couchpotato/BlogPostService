package com.example.service.impl;

import com.example.dto.postDTO.PostCreateRequestDTO;
import com.example.dto.postDTO.PostSearchRequestDTO;
import com.example.dto.postDTO.PostUpdateRequestDTO;
import com.example.dto.postDTO.PostCreateResponseDTO;
import com.example.dto.postDTO.PostResponseByIdDTO;
import com.example.dto.postDTO.PostSearchResponseDTO;
import com.example.entity.BlogAuthor;
import com.example.entity.BlogPost;
import com.example.entity.Tag;
import com.example.entity.types.PostStatus;
import com.example.repository.AuthorRepository;
import com.example.repository.PostRepository;
import com.example.repository.TagRepository;
import com.example.service.PostService;
import com.example.utils.AuthorityFinder;
import com.example.utils.DtoConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<PostSearchResponseDTO> findLatestPosts() {

        List<BlogPost> posts;
        if (AuthorityFinder.containsAuthority("admin.posts.ro")
        ) {
            posts = postRepository.findAll();
        } else {
            posts = postRepository.findAllByStatus(PostStatus.PUBLISHED);
        }
        return posts.stream()
                .sorted(Comparator.comparing(BlogPost::getCreatedOn).reversed())
                .map(DtoConverter::mapToDto)
                .toList();
    }


    @Override
    public PostCreateResponseDTO create(PostCreateRequestDTO postCreateRequestDTO) {

        BlogAuthor author = authorRepository.findById(postCreateRequestDTO.authorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UPGRADE_REQUIRED,
                        String.format("Blog with ID %d is in status [INACTIVE]", postCreateRequestDTO.authorId())));


        List<Tag> tagsList = postCreateRequestDTO.tagsList().stream()
                .map((Tag str) -> getTag(String.valueOf(str)))
                .toList();

        BlogPost blogPost = BlogPost.builder()
                .title(postCreateRequestDTO.title())
                .body(postCreateRequestDTO.body())
                .tags(tagsList)
                .status(PostStatus.PUBLISHED)
                .author(author)
                .createdOn(Instant.from(LocalDateTime.now()))
                .updatedOn(Instant.from(LocalDateTime.now())).build();

        return DtoConverter.mapToDtoCreate((postRepository.save(blogPost)));
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
    public List searchPosts(PostSearchRequestDTO request) {
        //TODO SEARCHING
        Map<String, Object> searchParams = new HashMap<>();
        StringBuilder query = new StringBuilder();

        query.append("from  bp where 1 = 1 ");

        if (request.authorsFirstName() != null) {
            query.append(" and bp.post.title = :title ");
            searchParams.put("name", request.authorsFirstName());
        }

        if (request.authorsLastName() != null) {
            query.append(" and bp.author = :author");
            searchParams.put("country", request.authorsLastName());
        }

        if (request.title() != null) {
            query.append(" and bp.title = :title");
            searchParams.put("title", request.title());
        }


        Query emQuery = entityManager.createQuery(query.toString());

//        searchParams.forEach((k, v) -> emQuery.setParameter(k, v));
        searchParams.forEach(emQuery::setParameter);

        return emQuery.getResultList();
    }

    @Override
    public PostResponseByIdDTO findPostById(Long id) {

        BlogPost post = postRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (AuthorityFinder.getAuthority().contains("admin.posts.ro")) {
           return DtoConverter.mapToDtoById(post);
        } else {
            post = postRepository.findBlogPostByIdAndStatus(id, PostStatus.PUBLISHED);
        }
        return DtoConverter.mapToDtoById(post);
    }


    @Override
    public void update(PostUpdateRequestDTO updateRequestDTO, Long postId) {

        BlogPost blogPost = postRepository.findById(postId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        blogPost.setTitle(updateRequestDTO.title());
        blogPost.setBody(updateRequestDTO.body());
        blogPost.setTags(updateRequestDTO.tags());
        blogPost.setUpdatedOn(Instant.now());

        //TODO:change tags option must be added

        postRepository.save(blogPost);
    }

    @Override
    public void setStatusBlockOrPublish(Long id) {

        BlogPost post = postRepository.findById(id).orElseThrow();

        if (post.getStatus().equals(PostStatus.BLOCKED)) {
            post.setStatus(PostStatus.PUBLISHED);
        } else if (post.getStatus().equals(PostStatus.PUBLISHED)) {
            post.setStatus(PostStatus.BLOCKED);
        } else {
            throw new IllegalArgumentException("The article has to be PUBLISHED or BLOCKED");
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
    public List<PostSearchResponseDTO> showArticlesByUserName(String name) {
        List<BlogPost> posts;
        if (AuthorityFinder.getAuthority().contains("admin.posts.ro")) {
            posts = postRepository.findAllByAuthor_Username(name);
        } else {
            posts = postRepository.findAllByAuthor_UsernameAndStatus(name,PostStatus.PUBLISHED);
        }
        return posts
                .stream()
                .map(DtoConverter::mapToDtoSearch)
                .toList();

    }

    @Override
    public void deleteById(long postId) {

        postRepository.deleteById(postId);
        log.info("Post with ID = {} deleted", postId);
    }

}





