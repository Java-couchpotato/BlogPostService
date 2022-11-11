package com.example;

import com.example.entity.BlogAuthor;
import com.example.entity.BlogPost;
import com.example.entity.Tag;
import com.example.entity.role.Role;
import com.example.entity.types.AccountStatus;
import com.example.entity.types.PostStatus;
import com.example.repository.AuthorRepository;
import com.example.repository.PostRepository;
import com.example.service.BlogUserPasswordService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Transactional
@AllArgsConstructor
public class Runner implements CommandLineRunner {

    private final BlogUserPasswordService passwordService;
    private final AuthorRepository userRepository;
    private final PostRepository postRepository;

    public static void main(String[] args) {

    }


    @Override
    public void run(String... args) throws Exception {


        BlogAuthor user = BlogAuthor.builder()
                .firstName("Ivan")
                .lastName("Ivanov")
                .username("ivashka")
                .isAdmin(false)
                .role(Role.ADMIN)
                .accountStatus(AccountStatus.ACTIVE)
                .createdOn(Instant.now())
                .build();


        userRepository.save(user);
        passwordService.generateAndSavePassword(user, "qwerty");
        //List<Tag> list = List.of(new Tag("Spring"));
        Tag tag1 = Tag.builder()
                .text("tag1")
                .build();
        Tag tag2 = Tag.builder()
                .text("tag2")
                .build();
        Tag tag3 = Tag.builder()
                .text("tag3")
                .build();
        List<Tag> tags = new ArrayList<>(Arrays.asList(tag1, tag2, tag3));

        BlogPost post = BlogPost.builder()
                .title("Spring and postgresql")
                .body("p>lorem ipsum bla bla bla spring postgresql")
                .author(user)
                .status(PostStatus.PUBLISHED)
                .tags(tags)
                .createdOn(Instant.now())
                .build();
        postRepository.save(post);
        BlogPost post1 = BlogPost.builder()
                .title("Spring and postgresql")
                .body("p>lorem ipsum bla bla bla spring postgresql")
                .author(user)
                .status(PostStatus.UNPUBLISHED)
                .tags(tags)
                .createdOn(Instant.now())
                .build();
        postRepository.save(post1);
        BlogPost post2 = BlogPost.builder()
                .title("Spring and postgresql")
                .body("p>lorem ipsum bla bla bla spring postgresql")
                .author(user)
                .status(PostStatus.BLOCKED)
                .tags(tags)
                .createdOn(Instant.now())
                .build();
        postRepository.save(post2);
    }
}
