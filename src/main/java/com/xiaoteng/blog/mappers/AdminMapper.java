package com.xiaoteng.blog.mappers;

import com.xiaoteng.blog.model.Admin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
    void insert(Admin admin);

    void delete(Long id);

    void update(Admin admin);

    List<Admin> all();

    Admin find(Long id);

    Admin findByUsername(String username);
}
