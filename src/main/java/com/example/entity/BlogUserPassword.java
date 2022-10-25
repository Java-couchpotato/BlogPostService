package com.example.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

@Entity
@Table(name = "blog_author_password ")
public class BlogUserPassword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_password_id")
    private Long id;

    @Column(name = "password")
    private String password;

    @Column(name = "salt")
    private String salt;

    @OneToOne
    @JoinColumn(name = "blog_author_id")
    private BlogAuthor user;

    public BlogUserPassword(BlogAuthor blogAuthor, String password, String salt) {
    }
}
