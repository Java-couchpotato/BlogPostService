package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "blog_author_session")
@Entity
public class BlogAuthorSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "session_id",unique = true,nullable = false,updatable = false)
    private String sessionId;
    @JoinColumn(name = "blog_author_id",nullable = false)
    @OneToOne
    private BlogAuthor blogAuthor;
}
