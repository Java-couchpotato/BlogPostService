package com.example.utils;

import com.example.dto.blogpost_response.BlogPostCreateResponseDTO;
import com.example.dto.blogpost_response.BlogPostResponseByIdDTO;
import com.example.dto.blogpost_response.BlogPostSearchResponseDTO;
import com.example.dto.user_response.AuthorResponseDTO;
import com.example.entity.BlogPost;
import com.example.entity.BlogUser;

import java.util.List;

public class BlogConverter {

    public static BlogPostSearchResponseDTO mapToDto(BlogPost blogPost) {
        return new BlogPostSearchResponseDTO(
                blogPost.getId(),
                blogPost.getTitle()
        );
    }

    public static BlogPostResponseByIdDTO mapToDtoById(BlogPost blogPost) {
        return new BlogPostResponseByIdDTO(
                blogPost.getId(),
                blogPost.getTitle(),
                blogPost.getBody(),
                blogPost.getTags(),
                blogPost.getAuthor(),
                blogPost.getStatus(),
                blogPost.getUpdatedOn(),
                blogPost.getCreatedOn()
        );
    }

    public static BlogPostCreateResponseDTO mapToDtoCreate(BlogPost blogPost) {
        return new BlogPostCreateResponseDTO(
                blogPost.getId(),
                blogPost.getTitle(),
                blogPost.getBody(),
                blogPost.getTags(),
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

    public static AuthorResponseDTO  mapToUserDto(BlogUser blogUser,Integer count) {
        return new AuthorResponseDTO(
                blogUser.getId(),
                blogUser.getFirstName(),
                blogUser.getLastName(),
                blogUser.getUsername(),
                count
        );
    }
    public static List<AuthorResponseDTO> mapToUserListDto(List<BlogUser> blogUsers,Integer count) {
        return blogUsers.stream()
                .map((BlogUser blogUser) -> mapToUserDto(blogUser,count))
                .toList();

    }
}
