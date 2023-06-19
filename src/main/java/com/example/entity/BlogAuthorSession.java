package com.example.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@Table(name = "blog_author_session")
@Entity
public class BlogAuthorSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID id;

    @Column(name = "session_id",
            unique = true,
            nullable = false,
            updatable = false)
    private String sessionId;


    @Column(name = "expiration_time")
    private LocalDateTime expirationTime;

    @JoinColumn(name = "blog_author_id",nullable = false)
    @OneToOne
    private BlogAuthor blogAuthor;
}