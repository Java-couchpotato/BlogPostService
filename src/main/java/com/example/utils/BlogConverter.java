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
        return  BlogPostSearchResponseDTO.builder()
                .blogId(blogPost.getId())
                .blogTitle(blogPost.getTitle())
                .build();
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
        return BlogPostCreateResponseDTO.builder()
                .id(blogPost.getId())
                .title(blogPost.getTitle())
                .body(blogPost.getBody())
                .tag(blogPost.getTags())
                .status(blogPost.getStatus())
                .authorId(blogPost.getId())
                .build();
    }

    public static BlogPostSearchResponseDTO mapToDtoSearch(BlogPost blogPost) {
        return BlogPostSearchResponseDTO.builder()
                .blogId(blogPost.getId())
                .blogTitle(blogPost.getTitle())
                .build();
    }


    public static List<BlogPostSearchResponseDTO> mapToDtoListSearch(List<BlogPost> blogPost) {

        return blogPost.stream()
                .map(BlogConverter::mapToDtoSearch)
                .toList();
    }

    public static AuthorResponseDTO mapToUserDto(BlogAuthor blogAuthor, Integer count) {
        return  AuthorResponseDTO.builder()
                .authorId(blogAuthor.getId())
                .authorFirstName(blogAuthor.getFirstName())
                .authorLastName(blogAuthor.getLastName())
                .authorUsername(blogAuthor.getUsername())
                .blogsCount(count)
                .build();
    }

    public static List<AuthorResponseDTO> mapToUserListDto(List<BlogAuthor> blogAuthors, Integer count) {
        return blogAuthors.stream()
                .map((BlogAuthor blogAuthor) -> mapToUserDto(blogAuthor, count))
                .toList();

    }
}
