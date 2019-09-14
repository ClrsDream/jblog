package com.xiaoteng.blog.mappers;

import com.xiaoteng.blog.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    void insert(User user);

    User findById(Long id);

    User findByEmail(String email);

    User findByNickname(String nickname);

    void updatePassword(Long id, String password);

    void updatePro(User user);

}
