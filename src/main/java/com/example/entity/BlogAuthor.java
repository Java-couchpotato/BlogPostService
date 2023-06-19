package com.example.entity;

import com.example.entity.converter.AccountStatusConverter;
import com.example.entity.role.Role;
import com.example.entity.types.AccountStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString
@Entity
@Table(name = "blog_author")
@EntityListeners(AuditingEntityListener.class)
public class BlogAuthor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID id;

    @Column(name = "updated_on")
    @LastModifiedDate
    private Instant updatedOn;

    @Column(name = "created_on")
    @LastModifiedDate
    private Instant createdOn;

    @Column(name = "username")
    private String username;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "account_status")
    @Enumerated(EnumType.STRING)
    @Convert(converter = AccountStatusConverter.class)
    private AccountStatus accountStatus;

    @Column(name = "roles")
    private Role role;

    @OneToMany
    @JoinColumn(name = "blog_post_id")
    private List<BlogPost>posts;

    @Column(name = "is_admin")
    private boolean isAdmin;


}
