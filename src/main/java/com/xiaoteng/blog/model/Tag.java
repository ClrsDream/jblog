package com.xiaoteng.blog.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Table(name = "tags")
public class Tag {

    @Id
    private Long id;

    @NotEmpty
    @Length(min = 1, max = 12)
    private String name;

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
}
