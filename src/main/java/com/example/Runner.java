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
    public void run(String... args) {


        BlogAuthor user = BlogAuthor.builder()
                .firstName("Hugh")
                .lastName("Auden")
                .username("wystan")
                .isAdmin(true)
                .role(Role.USER)
                .accountStatus(AccountStatus.ACTIVE)
                .createdOn(Instant.now())
                .build();


        userRepository.save(user);
        passwordService.generateAndSavePassword(user, "qwerty");

        BlogAuthor user2 = BlogAuthor.builder()
                .firstName("Steven")
                .lastName("King")
                .username("it")
                .isAdmin(true)
                .role(Role.ADMIN)
                .accountStatus(AccountStatus.ACTIVE)
                .createdOn(Instant.now())
                .build();

        userRepository.save(user2);
        passwordService.generateAndSavePassword(user2, "qwerty");

        Tag tag1 = Tag.builder()
                .text("stop all the clock")
                .build();
        Tag tag2 = Tag.builder()
                .text("cut of")
                .build();
        Tag tag3 = Tag.builder()
                .text("the telefon")
                .build();
        List<Tag> tags = new ArrayList<>(Arrays.asList(tag1, tag2, tag3));

        BlogPost post = BlogPost.builder()
                .title("dog stopping")
                .body("prevent the dog from barking \n with a juicy bone")
                .author(user)
                .status(PostStatus.PUBLISHED)
                .tags(tags)
                .createdOn(Instant.now())
                .build();
        postRepository.save(post);
        BlogPost post1 = BlogPost.builder()
                .title("bring out coffin")
                .body("silence the pianos and with muffled drum\n bring out the coffin")
                .author(user)
                .status(PostStatus.PUBLISHED)
                .tags(tags)
                .createdOn(Instant.now())
                .build();
        postRepository.save(post1);

        Tag tag4 = Tag.builder()
                .text("story")
                .build();
        Tag tag5 = Tag.builder()
                .text("plops")
                .build();

        List<Tag> tags2 = new ArrayList<>(Arrays.asList(tag4, tag5));

        BlogPost post2 = BlogPost.builder()
                .title("stars and moon")
                .body("the stars are not wanted now:put out every one /n,pack up the moon and dismantle the sun")
                .author(user)
                .status(PostStatus.BLOCKED)
                .tags(tags)
                .createdOn(Instant.now())
                .build();
        postRepository.save(post2);
        BlogPost post3 = BlogPost.builder()
                .title("horror")
                .body("You see something, then it clicks with something else,\n and it will make a story. But you never know when it's going to happen.\n")
                .author(user2)
                .status(PostStatus.PUBLISHED)
                .tags(tags2)
                .createdOn(Instant.now())
                .build();
        postRepository.save(post3); BlogPost post4 = BlogPost.builder()
                .title("laugh")
                .body("You can't deny laughter; when it comes, it plops down in your favorite chair and stays as long as it wants.")
                .author(user2)
                .status(PostStatus.BLOCKED)
                .tags(tags2)
                .createdOn(Instant.now())
                .build();
        postRepository.save(post4);
    }
}
