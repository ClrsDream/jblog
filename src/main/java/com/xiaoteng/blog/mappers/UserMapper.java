package com.xiaoteng.blog.mappers;

import com.xiaoteng.blog.model.User;
import com.xiaoteng.blog.model.UserPostFav;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    void insert(User user);

    User findById(Long id);

    User findByEmail(String email);

    User findByNickname(String nickname);

    void updatePassword(Long id, String password);

    void updatePro(User user);

    void postFav(Long userId, Long postId);

    void cancelPostFav(Long userId, Long postId);

    UserPostFav findPostFav(Long userId, Long postId);

    List<User> selectFavUsers(Long postId);
}
