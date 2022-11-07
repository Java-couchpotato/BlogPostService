package com.example.entity;

import com.example.entity.converter.AccountStatusConverter;
import com.example.entity.converter.RoleConverter;
import com.example.entity.role.Permission;
import com.example.entity.role.Role;
import com.example.entity.types.AccountStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder

@Entity
@Table(name = "blog_author")
public class BlogAuthor extends AbstractEntity{

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

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "updated_by_blog_author_id")
    private BlogAuthor updatedBy;

    @ManyToOne
    @JoinColumn(name = "created_by_blog_author_id")
    private BlogAuthor createdBy;

    @Column(name = "updated_on")
    @LastModifiedDate
    private Instant updatedOn;

    @Column(name = "created_on")
    @LastModifiedDate
    private Instant createdOn;

    @Column(name = "is_admin")
    private boolean isAdmin;


}
