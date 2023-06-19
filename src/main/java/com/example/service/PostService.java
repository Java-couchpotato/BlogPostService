package com.example.service;


import com.example.dto.postDTO.PostCreateResponseDTO;
import com.example.dto.postDTO.PostSearchRequestDTO;
import com.example.dto.postDTO.PostUpdateRequestDTO;
import com.example.dto.postDTO.PostCreateRequestDTO;
import com.example.dto.postDTO.PostResponseByIdDTO;
import com.example.dto.postDTO.PostSearchResponseDTO;

import java.util.List;
import java.util.UUID;

public interface PostService {


    List<PostSearchResponseDTO> findLatestPosts();

    PostCreateRequestDTO create(PostCreateResponseDTO postCreateResponseDTO);

    List<PostSearchResponseDTO> searchPosts(PostSearchRequestDTO searchRequestDTO);

    PostResponseByIdDTO findPostById(UUID id);

    void update(PostUpdateRequestDTO updateRequestDTO, UUID postId);

    void setStatusBlockOrPublish(UUID id);

    void setStatusUnpublished(UUID id);

    void setStatusPublished(UUID id);

    List<PostSearchResponseDTO> showArticlesByUserName(String name);

    void deleteById(UUID postId);
}
