package com.example.entity;

import com.example.entity.converter.PostStatusConverter;
import com.example.entity.types.PostStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.Instant;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString

@Entity
@Table(name = "blog_post ")
public class BlogPost extends AbstractEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "body")
    private String body;

    @ManyToOne
    @JoinColumn(name = "blog_author_id")
    private BlogAuthor author;

    @Enumerated(EnumType.STRING)
    @Column(name = "post_status")
    @Convert(converter = PostStatusConverter.class)
    private PostStatus status;

    @Column(name = "tag")
    private Tag tag;

    @Column(name = "updated_on")
    private Instant updatedOn;

    @Column(name = "created_on")
    @LastModifiedDate
    private Instant createdOn;

}
