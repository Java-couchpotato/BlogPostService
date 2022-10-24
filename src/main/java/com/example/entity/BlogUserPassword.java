package com.example.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

@Entity
@Table(name = "BlogUserPassword ")
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
    @JoinColumn(name = "user_blog_user_id")
    private BlogAuthor user;

    public BlogUserPassword(BlogAuthor blogAuthor, String password, String salt) {
    }
}
