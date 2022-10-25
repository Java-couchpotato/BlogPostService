package com.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id", nullable = false)
    private Long id;

    @Column(name = "role_name")
    private RoleName roleName;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<BlogAuthor> authors;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
