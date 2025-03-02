package com.xor.rest.rest_api_bb.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;



@Entity
@Table(
        name="posts",
        uniqueConstraints = @UniqueConstraint(columnNames = {"title"})
)

@Data
@AllArgsConstructor
public class Post {

    public Post() {}
    public Post(String title, String content, String description) {
        this.id = null;
        this.title = title;
        this.content = content;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name="content", nullable = false)
    private String content;

    @Column(name="description", nullable = false)
    private String description;



}
