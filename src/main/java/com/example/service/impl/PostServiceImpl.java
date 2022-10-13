package com.example.service.impl;

import com.example.dto.blogpost_request.PostCreateRequestDTO;
import com.example.dto.blogpost_request.PostSearchRequestDTO;
import com.example.dto.blogpost_request.PostUpdateRequestDTO;
import com.example.dto.blogpost_response.BlogPostCreateResponseDTO;
import com.example.dto.blogpost_response.BlogPostResponseByIdDTO;
import com.example.dto.blogpost_response.BlogPostSearchResponseDTO;
import com.example.entity.AccountStatus;
import com.example.entity.BlogPost;
import com.example.entity.BlogUser;
import com.example.entity.PostStatus;
import com.example.repository.BlogUserRepository;
import com.example.repository.PostRepository;
import com.example.service.PostService;
import com.example.utils.BlogConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;

@Transactional
@Slf4j
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private BlogUserRepository blogUserRepository;


    @Override
    public List<BlogPostSearchResponseDTO> findPosts() {
        return postRepository.findAll().stream()
                .filter(status -> status.getStatus().equals(PostStatus.PUBLISHED))
                //.sorted(Comparator.comparing(BlogPost::getCreatedOn).reversed())
                .map(BlogConverter::mapToDto)
                .toList();
    }

    @Override
    public BlogPostCreateResponseDTO create(PostCreateRequestDTO postCreateRequestDTO) {

        BlogUser author = blogUserRepository.findById(postCreateRequestDTO.getAuthorId())
                .orElseThrow();

        if (author.getAccountStatus() == AccountStatus.INACTIVE) {
            throw new ResponseStatusException(HttpStatus.UPGRADE_REQUIRED,
                    String.format("Blog with ID %D is in status [INACTIVE]", author.getId()));
        }

        BlogPost blogPost = new BlogPost(
                postCreateRequestDTO.title,
                postCreateRequestDTO.body,
                postCreateRequestDTO.tags,
                postCreateRequestDTO.authorId
        );

        return BlogConverter.mapToDtoCreate((postRepository.save(blogPost)));
    }

    @Override
    public List<BlogPostSearchResponseDTO> search(List<PostSearchRequestDTO> searchRequestDTO) {

        return BlogConverter.mapToDtoListSearch(postRepository.findAll().stream()
                .filter(status -> status.getStatus().equals(PostStatus.PUBLISHED)).toList());
    }

    @Override
    public BlogPostResponseByIdDTO findPostById(Long id) {
        return (BlogPostResponseByIdDTO) postRepository.findById(id).stream()
                .map(BlogConverter::mapToDtoById);
    }


    @Override
    public void update(PostUpdateRequestDTO updateRequestDTO, Long postId) {

        BlogPost blogPost = postRepository.findById(postId).orElseThrow();

        blogPost.setTitle(updateRequestDTO.title);
        blogPost.setBody(updateRequestDTO.body);
        blogPost.setTags(updateRequestDTO.tags);

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
    public List<BlogPostSearchResponseDTO> showArticlesByUser(String name) {

        return postRepository.findAllByAuthor(name)
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
