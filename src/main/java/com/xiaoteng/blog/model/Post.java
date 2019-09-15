package com.xiaoteng.blog.model;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Table(name = "posts")
public class Post {

    @Id
    private Long id;

    private Long userId;

    // 帖子标题
    @NotEmpty
    @Length(min = 2, max = 200)
    private String title;

    // 帖子内容
    @NotEmpty
    @Length(min = 5, max = 100000)
    private String content;
    // 阅读次数
    private Long readNum;
    // 用户喜欢数量
    private int userFavCount;
    // 评论数量
    private int commentsCount;
    // 帖子创建时间
    @CreatedDate
    private Date createdAt;
    // 帖子最后编辑时间
    @LastModifiedDate
    private Date updatedAt;
    // 帖子发布时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishedAt;
    // 作者
    private User user;

    public Post() {

    }

    public Post(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", readNum=" + readNum +
                ", userFavCount=" + userFavCount +
                ", commentsCount=" + commentsCount +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", publishedAt=" + publishedAt +
                ", user=" + user +
                '}';
    }

    public int getUserFavCount() {
        return userFavCount;
    }

    public void setUserFavCount(int userFavCount) {
        this.userFavCount = userFavCount;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getReadNum() {
        return readNum;
    }

    public void setReadNum(Long readNum) {
        this.readNum = readNum;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

}
