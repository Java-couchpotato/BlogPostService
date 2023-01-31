package com.example.service;


import com.example.dto.postDTO.PostCreateRequestDTO;
import com.example.dto.postDTO.PostSearchRequestDTO;
import com.example.dto.postDTO.PostUpdateRequestDTO;
import com.example.dto.postDTO.PostCreateResponseDTO;
import com.example.dto.postDTO.PostResponseByIdDTO;
import com.example.dto.postDTO.PostSearchResponseDTO;

import java.util.List;

public interface PostService {


    List<PostSearchResponseDTO> findLatestPosts();

    PostCreateResponseDTO create(PostCreateRequestDTO postCreateRequestDTO);

    List<PostSearchResponseDTO> searchPosts(PostSearchRequestDTO searchRequestDTO);

    PostResponseByIdDTO findPostById(Long id);

    void update(PostUpdateRequestDTO updateRequestDTO, Long postId);

    void setStatusBlockOrPublish(Long id);

    void setStatusUnpublished(Long id);

    void setStatusPublished(Long id);

    List<PostSearchResponseDTO> showArticlesByUserName(String name);

    void deleteById(long postId);
}
