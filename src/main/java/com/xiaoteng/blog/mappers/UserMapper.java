package com.xiaoteng.blog.mappers;

import com.xiaoteng.blog.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    void insert(User user);

    User findById(Long id);

    User findByEmail(String email);

    User findByNickname(String nickname);

    void updatePassword(Long id, String password);

    void updatePro(User user);

    void createPostFav(Long userId, Long postId);

    void cancelPostFav(Long userId, Long postId);

    Long findPostFav(Long userId, Long postId);

    List<User> selectFavUsers(Long postId);

    List<User> paginate(@Param("user") User user, @Param("start") int start, @Param("pageSize") int pageSize);

    Long count(@Param("user") User user);

    void update(@Param("user") User user, @Param("id") Long id);
}
