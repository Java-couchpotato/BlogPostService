package com.example.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tag")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Tag extends AbstractEntity implements Serializable {

    @Column(name = "text")
    private String text;

    @ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
    private List<BlogPost> posts = new ArrayList<>();

    public void setText(String text) {
        this.text = text;
    }
}