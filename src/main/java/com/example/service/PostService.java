package com.example.service;


import com.example.dto.blogpost_request.PostCreateRequestDTO;
import com.example.dto.blogpost_request.PostSearchRequestDTO;
import com.example.dto.blogpost_request.PostUpdateRequestDTO;
import com.example.dto.blogpost_response.BlogPostCreateResponseDTO;
import com.example.dto.blogpost_response.BlogPostResponseByIdDTO;
import com.example.dto.blogpost_response.BlogPostSearchResponseDTO;
import com.example.entity.PostStatus;

import java.util.List;

public interface PostService {


    List<BlogPostSearchResponseDTO> findPosts();

    BlogPostCreateResponseDTO create(PostCreateRequestDTO postCreateRequestDTO);

    List<BlogPostSearchResponseDTO> search(List<PostSearchRequestDTO> searchRequestDTO);

    BlogPostResponseByIdDTO findPostById(Long id);

    void update(PostUpdateRequestDTO updateRequestDTO, Long postId);

    void setStatusBlocked(Long id);

    void setStatusUnpublished(Long id);

    void setStatusPublished(Long id);

    List<BlogPostSearchResponseDTO> showArticlesByUser(String name);

    void deleteById(long postId);
}
