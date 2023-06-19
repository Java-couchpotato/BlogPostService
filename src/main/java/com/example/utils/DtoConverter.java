package com.example.utils;

import com.example.dto.adminDTO.AdminShowAuthorsDTO;
import com.example.dto.postDTO.PostCreateRequestDTO;
import com.example.dto.postDTO.PostInfoResponseDTO;
import com.example.dto.postDTO.PostResponseByIdDTO;
import com.example.dto.postDTO.PostSearchResponseDTO;
import com.example.dto.authorDTO.*;
import com.example.entity.BlogAuthor;
import com.example.entity.BlogPost;

import java.util.List;

public class DtoConverter {

//    public static PostSearchResponseDTO mapToDto(BlogPost blogPost) {
//        return  PostSearchResponseDTO.builder()
//                .blogId(blogPost.getId())
//                .blogTitle(blogPost.getTitle())
//                .build();
//    }
//
//    public static PostSearchResponseDTO mapToDtoSearch(BlogPost blogPost) {
//        return PostSearchResponseDTO.builder()
//                .blogId(blogPost.getId())
//                .blogTitle(blogPost.getTitle())
//                .build();
//    }
//
//
//    public static PostResponseByIdDTO mapToDtoById(BlogPost blogPost) {
//        return PostResponseByIdDTO.builder()
//                .id(blogPost.getId())
//                .title(blogPost.getTitle())
//                .body(blogPost.getBody())
//                .author(String.valueOf(blogPost.getAuthor()))
//                .status(blogPost.getAuthor().getAccountStatus())
//                .createdDate(blogPost.getCreatedOn())
//                .updatedDate(blogPost.getUpdatedOn())
//                .build();
//    }
//
//    public static PostCreateRequestDTO mapToDtoCreate(BlogPost blogPost) {
//        return PostCreateRequestDTO.builder()
//                .id(blogPost.getId())
//                .title(blogPost.getTitle())
//                .body(blogPost.getBody())
//                .tag(blogPost.getTags())
//                .status(blogPost.getStatus())
//                .authorId(blogPost.getId())
//                .build();
//    }
//
//
//    public static List<PostSearchResponseDTO> mapToDtoListSearch(List<BlogPost> blogPost) {
//
//        return blogPost.stream()
//                .map(DtoConverter::mapToDtoSearch)
//                .toList();
//    }
//
//    public static AuthorResponseDTO mapToAuthorByCountDto(BlogAuthor blogAuthor, Integer count) {
//        return  AuthorResponseDTO.builder()
//                .authorId(blogAuthor.getId())
//                .authorFirstName(blogAuthor.getFirstName())
//                .authorLastName(blogAuthor.getLastName())
//                .authorUsername(blogAuthor.getUsername())
//                .blogsCount(count)
//                .build();
//    }
//    public static AdminShowAuthorsDTO mapToAdminShows(BlogAuthor author){
//        return AdminShowAuthorsDTO.builder()
//                .id(author.getId())
//                .name(author.getFirstName())
//                .username(author.getUsername())
//                .isAdmin(author.isAdmin())
//                .permissions(author.getRole().getPermissions())
//                .build();
//    }
//
//    public static List<AdminShowAuthorsDTO> mapToAdminShowsListDto(List<BlogAuthor> blogAuthors) {
//        return blogAuthors.stream()
//                .map(DtoConverter::mapToAdminShows)
//                .toList();
//
//    }
//    public static AuthorWithArticlesResponseDTO mapToAuthorAndArticles(BlogAuthor author,List<PostInfoResponseDTO> post){
//        return AuthorWithArticlesResponseDTO.builder()
//                .authorId(author.getId())
//                .authorFirstName(author.getFirstName())
//                .authorLastName(author.getLastName())
//                .authorUsername(author.getUsername())
//                .blogs(post)
//                .build();
//    }
//    public static PostInfoResponseDTO mapToDtoAuthorBlogs(BlogPost blogPost) {
//        return PostInfoResponseDTO.builder()
//                .id(blogPost.getId())
//                .title(blogPost.getTitle())
//                .status(blogPost.getStatus())
//                .build();
//    }
}
