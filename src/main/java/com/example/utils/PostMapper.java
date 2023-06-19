package com.example.utils;

import com.example.dto.postDTO.*;
import com.example.entity.BlogPost;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

   PostCreateResponseDTO entityToCreateRequest(BlogPost post);
   PostSearchResponseDTO entityToSearchRequest(BlogPost post);
   PostResponseByIdDTO entityToDtoById(BlogPost blogPost);
   PostCreateRequestDTO mapToDtoCreate(BlogPost blogPost);
   PostInfoResponseDTO mapToDtoAuthorBlogs(BlogPost blogPost);
   List<PostSearchResponseDTO> mapToDtoListSearch(List<BlogPost> blogPost);
   List<PostInfoResponseDTO> mapToPostInfoResponseDTO(List<BlogPost> blogPost);
}
