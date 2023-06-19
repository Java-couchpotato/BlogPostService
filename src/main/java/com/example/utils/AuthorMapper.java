package com.example.utils;

import com.example.dto.adminDTO.AdminShowAuthorsDTO;
import com.example.dto.authorDTO.AuthorResponseDTO;
import com.example.dto.authorDTO.AuthorWithArticlesResponseDTO;
import com.example.dto.postDTO.PostInfoResponseDTO;
import com.example.entity.BlogAuthor;
import com.example.entity.BlogPost;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorResponseDTO mapToAuthorByCountDto(BlogAuthor blogAuthor);

    AdminShowAuthorsDTO mapToAdminShows(BlogAuthor author);

    List<AdminShowAuthorsDTO> mapToAdminShowsListDto(List<BlogAuthor> blogAuthors);

    AuthorWithArticlesResponseDTO mapToAuthorAndArticles(BlogAuthor author);

    PostInfoResponseDTO mapToDtoAuthorBlogs(BlogPost blogPost);

    List<AuthorResponseDTO> mapToResponseDtoList(List<BlogAuthor>author);
}
