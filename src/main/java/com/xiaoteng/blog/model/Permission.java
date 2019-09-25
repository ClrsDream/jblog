package com.xiaoteng.blog.model;

import javax.persistence.Table;

@Table(name = "permissions")
public class Permission {

    private Long id;
    private String name;
    private String httpMethod;
    private String uri;
    private String frontendNode;

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

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getFrontendNode() {
        return frontendNode;
    }

    public void setFrontendNode(String frontendNode) {
        this.frontendNode = frontendNode;
    }
}
