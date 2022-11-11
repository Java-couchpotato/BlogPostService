package com.example.service;


import com.example.dto.AuthorInfoDTO;
import com.example.dto.request.PostCreateRequestDTO;
import com.example.dto.request.PostSearchRequestDTO;
import com.example.dto.request.PostUpdateRequestDTO;
import com.example.dto.request.UserInfoDto;
import com.example.dto.response.AuthorRoleResponseDTO;
import com.example.dto.response.BlogPostCreateResponseDTO;
import com.example.dto.response.BlogPostResponseByIdDTO;
import com.example.dto.response.BlogPostSearchResponseDTO;

import java.security.Principal;
import java.util.List;

public interface PostService {


    List<BlogPostSearchResponseDTO> findLatestPosts(String header);

    BlogPostCreateResponseDTO create(PostCreateRequestDTO postCreateRequestDTO);

    List<BlogPostSearchResponseDTO> searchPosts(PostSearchRequestDTO searchRequestDTO);

    BlogPostResponseByIdDTO findPostById(Long id, UserInfoDto userInfoDto);

    void update(PostUpdateRequestDTO updateRequestDTO, Long postId);

    void setStatusBlocked(Long id);

    void setStatusUnpublished(Long id);

    void setStatusPublished(Long id);

    List<BlogPostSearchResponseDTO> showArticlesByUserName(String name);

    void deleteById(long postId);
}
