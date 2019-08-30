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
    @Column(name = "password", nullable = false, columnDefinition = "varchar(128)")
    private String password;

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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", lastLoginAt=" + lastLoginAt +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public Date getLastLoginAt() {
        return lastLoginAt;
    }

    public void setLastLoginAt(Date lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }
}
