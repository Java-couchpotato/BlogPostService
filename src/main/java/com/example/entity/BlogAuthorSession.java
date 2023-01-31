package com.example.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@Table(name = "blog_author_session")
@Entity
public class BlogAuthorSession extends AbstractEntity{


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