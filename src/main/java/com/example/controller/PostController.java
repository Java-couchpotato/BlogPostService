package com.example.controller;

import com.example.dto.AuthorInfoDTO;
import com.example.dto.request.PostCreateRequestDTO;
import com.example.dto.request.PostSearchRequestDTO;
import com.example.dto.request.PostUpdateRequestDTO;
import com.example.dto.request.UserInfoDto;
import com.example.dto.response.BlogPostCreateResponseDTO;
import com.example.dto.response.BlogPostResponseByIdDTO;
import com.example.dto.response.BlogPostSearchResponseDTO;
import com.example.entity.types.PostStatus;
import com.example.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/posts")
public class PostController {


    private final PostService blogPostService;

    @GetMapping
    public List<BlogPostSearchResponseDTO> showPosts( String principal) {
        return blogPostService.findLatestPosts(principal);
    }

    @PostMapping("/create")
    //@ResponseStatus(HttpStatus.CREATED)
    public BlogPostCreateResponseDTO createPost(
            @RequestBody PostCreateRequestDTO requestDTO) {

        return blogPostService.create(requestDTO);
    }

    @PostMapping("/search")
    public List<BlogPostSearchResponseDTO> searchPost(
            @RequestBody PostSearchRequestDTO requestDTO) {

        return blogPostService.searchPosts(requestDTO);
    }

    @GetMapping("/{id}")
    public BlogPostResponseByIdDTO showPostsById(@AuthenticationPrincipal UserInfoDto userInfoDto,
            @PathVariable Long id) {

        return blogPostService.findPostById(id,userInfoDto);
    }

    @PatchMapping("update/{id}")
    public void updateArticle(
            @RequestBody PostUpdateRequestDTO updateRequestDTO, @PathVariable Long id) {

        blogPostService.update(updateRequestDTO, id);
    }

    @PutMapping("/publish/{id}")
    public void setStatusPublish(
            @PathVariable Long id,
            @PathVariable PostStatus publish
    ) {
        blogPostService.setStatusPublished(id);
    }

    @PutMapping("/unpublish/{id}")
    public void setStatusUnpublish(
            @PathVariable Long id,
            @PathVariable PostStatus unpublish
    ) {
        blogPostService.setStatusUnpublished(id);
    }

    @PutMapping("/block/{id}")
    public void setStatusBlock(
            @PathVariable Long id,
            @PathVariable PostStatus block
    ) {
        blogPostService.setStatusBlocked(id);
    }

    @GetMapping("/username/{username}")
    public List<BlogPostSearchResponseDTO> showPostByUser(
            @PathVariable String username
    ) {
        return blogPostService.showArticlesByUserName(username);
    }

    @PreAuthorize("hasAuthority('ADMIN')||#id==authentication.principal.blogUser.id")
    @DeleteMapping("/delete/{id}")
    public void deleteArticle(
            @PathVariable Long id
          ) {

        blogPostService.deleteById(id);
    }

}
