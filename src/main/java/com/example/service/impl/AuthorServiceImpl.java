package com.example.service.impl;

import com.example.dto.authorDTO.AuthorResponseDTO;
import com.example.dto.authorDTO.AuthorWithArticlesResponseDTO;
import com.example.entity.BlogAuthor;
import com.example.entity.BlogPost;
import com.example.entity.types.PostStatus;
import com.example.repository.AuthorRepository;
import com.example.repository.PostRepository;
import com.example.service.BlogAuthorService;
import com.example.utils.AuthorityFinder;
import com.example.utils.DtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
@Transactional
public class AuthorServiceImpl implements BlogAuthorService {

    private final AuthorRepository authorRepository;

    private final PostRepository postRepository;


    @Override
    public List<AuthorResponseDTO> showAuthorsByArticlesCount() {

        return getMapOfAuthorAndPublishedPosts().entrySet().stream()
                .map(a -> DtoConverter.mapToAuthorByCountDto(a.getKey(), a.getValue().size())).toList()
                .stream().sorted(Comparator.comparing(AuthorResponseDTO::blogsCount).reversed()).toList();


    }

    private Map<BlogAuthor, List<BlogPost>> getMapOfAuthorAndPublishedPosts() {
        return postRepository.findAll().stream()
                .filter(k -> k.getStatus().equals(PostStatus.PUBLISHED))
                .collect(Collectors.groupingBy(BlogPost::getAuthor));
    }


    @Override
    public AuthorWithArticlesResponseDTO showAuthorsWithArticles(Long id) {
        AuthorWithArticlesResponseDTO authorPosts;
        var author = authorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND
                        , String.format("Author with id %d does not exists", id)));

        var posts = postRepository.findAllByAuthorIdOrderByCreatedOnDesc(author.getId())
                .stream()
                .map(DtoConverter::mapToDtoAuthorBlogs).toList();

        var postsPublished = postRepository.findAllByAuthorIdOrderByCreatedOnDesc(author.getId())
                .stream()
                .filter(p -> p.getStatus().equals(PostStatus.PUBLISHED))
                .map(DtoConverter::mapToDtoAuthorBlogs).toList();


        if (AuthorityFinder.getAuthority().contains("admin.posts.ro")) {
            authorPosts = DtoConverter.mapToAuthorAndArticles(author, posts);
        } else {
            authorPosts = DtoConverter.mapToAuthorAndArticles(author, postsPublished);
        }


        return authorPosts;
    }
}
