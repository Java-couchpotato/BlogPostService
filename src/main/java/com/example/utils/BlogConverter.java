package com.example.utils;

import com.example.dto.response.AuthorResponseDTO;
import com.example.dto.response.BlogPostCreateResponseDTO;
import com.example.dto.response.BlogPostResponseByIdDTO;
import com.example.dto.response.BlogPostSearchResponseDTO;
import com.example.entity.BlogAuthor;
import com.example.entity.BlogPost;

import java.util.List;

public class BlogConverter {

    public static BlogPostSearchResponseDTO mapToDto(BlogPost blogPost) {
        return new BlogPostSearchResponseDTO(
                blogPost.getId(),
                blogPost.getTitle()
        );
    }

    public static BlogPostResponseByIdDTO mapToDtoById(BlogPost blogPost) {
        return BlogPostResponseByIdDTO.builder()
                .id(blogPost.getId())
                .title(blogPost.getTitle())
                .body(blogPost.getBody())
                .author(String.valueOf(blogPost.getAuthor()))
                .status(blogPost.getAuthor().getAccountStatus())
                .createdDate(blogPost.getCreatedOn())
                .updatedDate(blogPost.getUpdatedOn())
                .build();
    }

    public static BlogPostCreateResponseDTO mapToDtoCreate(BlogPost blogPost) {
        return new BlogPostCreateResponseDTO(
                blogPost.getId(),
                blogPost.getTitle(),
                blogPost.getBody(),
                blogPost.getStatus(),
                blogPost.getAuthor().getId()
        );
    }

    public static BlogPostSearchResponseDTO mapToDtoSearch(BlogPost blogPost) {
        return new BlogPostSearchResponseDTO(
                blogPost.getId(),
                blogPost.getTitle()
        );
    }


    public static List<BlogPostSearchResponseDTO> mapToDtoListSearch(List<BlogPost> blogPost) {

        return blogPost.stream()
                .map(BlogConverter::mapToDtoSearch)
                .toList();
    }

    public static AuthorResponseDTO mapToUserDto(BlogAuthor blogAuthor, Integer count) {
        return new AuthorResponseDTO(
                blogAuthor.getId(),
                blogAuthor.getFirstName(),
                blogAuthor.getLastName(),
                blogAuthor.getUsername(),
                count
        );
    }

    public static List<AuthorResponseDTO> mapToUserListDto(List<BlogAuthor> blogAuthors, Integer count) {
        return blogAuthors.stream()
                .map((BlogAuthor blogAuthor) -> mapToUserDto(blogAuthor, count))
                .toList();

    }
}
