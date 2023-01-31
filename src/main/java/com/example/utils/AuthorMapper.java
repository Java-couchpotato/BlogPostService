package com.example.utils;

import com.example.dto.postDTO.*;
import com.example.entity.BlogPost;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    PostCreateRequestDTO toRequestCreatePostDto(BlogPost post);

    PostCreateResponseDTO toResponseCreatePostDto(BlogPost post);

    PostInfoResponseDTO toPostInfo(BlogPost post);

    PostResponseByIdDTO toPostResponseId(BlogPost post);

    PostSearchRequestDTO toPostSearchRequest(BlogPost post);

    PostSearchResponseDTO toPostSearchResponse(BlogPost post);

    PostUpdateRequestDTO toPostUpdate(BlogPost post);
}
