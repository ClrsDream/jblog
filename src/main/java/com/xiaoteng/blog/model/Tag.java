package com.xiaoteng.blog.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "tags", uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Length(min = 1, max = 12)
    @Column(name = "name", nullable = false)
    private String name;

    // 关联的帖子
    @JoinTable(name = "post_tag", joinColumns = @JoinColumn(name = "tag_id"), inverseJoinColumns = @JoinColumn(name = "post_id"))
    @ManyToMany
    private List<Post> posts;

    public Tag(@NotEmpty @Length(min = 1, max = 12) String name) {
        this.name = name;
    }

    public Tag() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
