package com.example.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder

@Entity
@Table(name = "blog_author_password ")
public class BlogAuthorPassword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "password")
    private String password;

    @Column(name = "salt")
    private String salt;

    @OneToOne
    @JoinColumn(name = "blog_author_id")
    private BlogAuthor user;

}
