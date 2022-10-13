package com.example.controller;

import com.example.dto.blogpost_request.PostCreateRequestDTO;
import com.example.dto.blogpost_request.PostSearchRequestDTO;
import com.example.dto.blogpost_request.PostUpdateRequestDTO;
import com.example.dto.blogpost_response.BlogPostCreateResponseDTO;
import com.example.dto.blogpost_response.BlogPostResponseByIdDTO;
import com.example.dto.blogpost_response.BlogPostSearchResponseDTO;
import com.example.entity.PostStatus;
import com.example.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService blogPostService;

    @GetMapping
    public List<BlogPostSearchResponseDTO> showPosts() {

        return blogPostService.findPosts();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public BlogPostCreateResponseDTO createPost(
            @RequestBody PostCreateRequestDTO requestDTO) {

        return blogPostService.create(requestDTO);
    }

    @PostMapping("/search")
    public List<BlogPostSearchResponseDTO> searchPost(
            @RequestBody List<PostSearchRequestDTO> requestDTO) {

        return blogPostService.search(requestDTO);
    }

    @GetMapping("{id}")
    public BlogPostResponseByIdDTO showPostsById(
            @PathVariable Long blogId) {

        return blogPostService.findPostById(blogId);
    }

    @PatchMapping("/{id}")
    public void updateArticle(
            @PathVariable Long blogId,
            @RequestBody PostUpdateRequestDTO updateRequestDTO) {

        blogPostService.update(updateRequestDTO, blogId);
    }

    @PutMapping("/{id}/{status}")
    public void setStatusPublish(
            @PathVariable Long blogId,
            @PathVariable PostStatus status
    ) {
        blogPostService.setStatusPublished(blogId);
    }

    @PutMapping("/{id}/{status1}")
    public void setStatusUnpublish(
            @PathVariable Long blogId,
            @PathVariable PostStatus status1
    ) {
        blogPostService.setStatusUnpublished(blogId);
    }

    @PutMapping("/{id}/{status2}")
    public void setStatusBlock(
            @PathVariable Long blogId,
            @PathVariable PostStatus status2
    ) {
        blogPostService.setStatusBlocked(blogId);
    }

    @GetMapping("/{username}")
    public List<BlogPostSearchResponseDTO> showPostByUser(
            @PathVariable String username
    ) {
        return blogPostService.showArticlesByUser(username);
    }
    @DeleteMapping("/{id}")
    public void deleteArticle(
            @PathVariable Long postId
          ) {

        blogPostService.deleteById(postId);
    }

}
