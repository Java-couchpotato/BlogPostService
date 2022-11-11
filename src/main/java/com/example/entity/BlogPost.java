package com.example.entity;

import com.example.entity.converter.PostStatusConverter;
import com.example.entity.types.PostStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;


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

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "post_tag",
            joinColumns = {
                    @JoinColumn(name = "blog_post_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "tag_id", referencedColumnName = "id")
            })
    private List<Tag> tags;

    @Column(name = "updated_on")
    private Instant updatedOn;

    @Column(name = "created_on")
    @LastModifiedDate
    private Instant createdOn;

}
