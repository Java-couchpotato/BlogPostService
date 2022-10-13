package com.example.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString

@Entity
@Table(name = "BlogPost ")
public class BlogPost {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_post")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "body")
    private String body;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "blog_user_id")
    private BlogUser author;

    @Enumerated(EnumType.STRING)
    private PostStatus status;


    @ElementCollection
    private Set<String> tags = new HashSet<>();

    //@Temporal(TemporalType.DATE)
    private Instant updatedOn;

   // @Temporal(TemporalType.DATE)
    private Instant createdOn;


    public BlogPost(String title, String body, Set<String> tags, Long authorId) {
    }
}
