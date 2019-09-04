package com.xiaoteng.blog.model;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Email
    @Length(min = 6, max = 32)
    @Column(name = "email", nullable = false, columnDefinition = "varchar(32)")
    private String email;

    @NotEmpty
    @Length(min = 1, max = 10)
    @Column(name = "nickname", nullable = false, columnDefinition = "varchar(10)")
    private String nickname;

    @Column(name = "avatar", nullable = false, columnDefinition = "varchar(255) default ''")
    private String avatar;

    @NotEmpty
    @Column(name = "password", nullable = false, columnDefinition = "varchar(128)")
    private String password;

    // 微博
    @Column(name = "weibo", nullable = false, columnDefinition = "varchar(32) default ''")
    private String Weibo;

    // qq
    @Column(name = "qq", nullable = false, columnDefinition = "varchar(16) default ''")
    private String Qq;

    // Github
    @Column(name = "github", nullable = false, columnDefinition = "varchar(32) default ''")
    private String Github;

    // 个人简介
    @Column(name = "intro", nullable = false, columnDefinition = "varchar(255) default ''")
    private String Intro;

    // 状态[0正常,1禁止回复,2禁止发帖,3禁止回复+发帖,4禁止登录]
    @Column(name = "status", nullable = false, columnDefinition = "tinyint(2) default 0")
    private Integer status;

    // 最后登录时间
    @CreatedDate
    @Column(name = "last_login_at", columnDefinition = "timestamp")
    private Date lastLoginAt;

    // 创建时间
    @CreatedDate
    @Column(name = "created_at", nullable = false, columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private Date createdAt;

    // 最后编辑时间
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false, columnDefinition = "timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updatedAt;

    public User(@NotEmpty @Length(min = 6, max = 32) String email, @NotEmpty @Length(min = 6, max = 16) String password, Integer status) {
        this.email = email;
        this.password = password;
        this.status = status;
    }

    public User() {
    }

    public String getWeibo() {
        return Weibo;
    }

    public void setWeibo(String weibo) {
        Weibo = weibo;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", password='" + password + '\'' +
                ", Weibo='" + Weibo + '\'' +
                ", Qq='" + Qq + '\'' +
                ", Github='" + Github + '\'' +
                ", Intro='" + Intro + '\'' +
                ", status=" + status +
                ", lastLoginAt=" + lastLoginAt +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getQq() {
        return Qq;
    }

    public void setQq(String qq) {
        Qq = qq;
    }

    public String getGithub() {
        return Github;
    }

    public void setGithub(String github) {
        Github = github;
    }

    public String getIntro() {
        return Intro;
    }

    public void setIntro(String intro) {
        Intro = intro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getLastLoginAt() {
        return lastLoginAt;
    }

    public void setLastLoginAt(Date lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }
}
