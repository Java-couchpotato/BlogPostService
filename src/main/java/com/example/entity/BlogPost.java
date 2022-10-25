package com.example.entity;

import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString

@Entity
@Table(name = "blog_post ")
public class BlogPost {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_post_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "body")
    private String body;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "blog_author_id")
    private BlogAuthor author;

    @Enumerated(EnumType.STRING)
    @Column(name = "post_status")
    private PostStatus status;

    @ElementCollection
    private Set<String> tags = new HashSet<>();

    @Column(name = "updated_on")
    private Instant updatedOn;

    @Column(name = "created_on")
    @LastModifiedDate
    private Instant createdOn;



}
