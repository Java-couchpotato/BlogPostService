package com.example.entity;

import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

@Entity
@Table(name = "blog_author")
public class BlogAuthor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_author_id")
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

    @Convert(converter = RoleConverter.class)
    @Column(name = "rolename", nullable = false)
    private RoleName roleName;

    @Column(name = "updated_on")
    @LastModifiedDate
    private Instant updatedOn;

    @Column(name = "created_on")
    @LastModifiedDate
    private Instant createdOn;

    @Column(name = "is_admin")
    private boolean isAdmin;

    @ManyToMany(fetch =FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "roles",
    joinColumns = {
            @JoinColumn(name = "blog_author_id")},
    inverseJoinColumns = {
            @JoinColumn(name = "role_id")})
    private List<Role>roles;

    public BlogAuthor(String authorFirstName, String authorLastName, String authorUsername) {
    }
}
