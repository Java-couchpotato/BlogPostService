package com.example.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

@Entity
@Table(name = "BlogUser")
public class BlogUser {

   // @Temporal(TemporalType.DATE)
    Instant updatedOn;

   // @Temporal(TemporalType.DATE)
    Instant createdOn;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_user_id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "account_status")
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    public BlogUser(String authorFirstName, String authorLastName, String authorUsername) {
    }
}
