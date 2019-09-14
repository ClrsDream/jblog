package com.xiaoteng.blog.service;

import com.xiaoteng.blog.enums.UserStatusEnum;
import com.xiaoteng.blog.mappers.UserMapper;
import com.xiaoteng.blog.model.Post;
import com.xiaoteng.blog.model.User;
import com.xiaoteng.blog.repositories.UserRepository;
import com.xiaoteng.blog.utils.HashTool;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Value("${blog.user.defaultAvatar}")
    private String defaultAvatar;

    @Autowired
    private UserMapper userMapper;

    public User findUserByEmail(String email) {
        return userMapper.findByEmail(email);
    }

    public User findUserById(Long id) {
        return userMapper.findById(id);
    }

    public User findUserByNickname(String nickname) {
        return userMapper.findByNickname(nickname);
    }

    /**
     * 获取已登录用户
     *
     * @return User
     */
    public User getUser() {
        return (User) SecurityUtils.getSubject().getPrincipal();
    }

    public User createUser(User user) {
        user.setPassword(HashTool.encode(user.getPassword()));
        user.setStatus(UserStatusEnum.NORMAL.getStatus());
        user.setAvatar(defaultAvatar);
        userMapper.insert(user);

        return user;
    }

    public void updatePro(User user) {
        userMapper.updatePro(user);
    }

    public void setPassword(User user, String password) {
        userMapper.updatePassword(user.getId(), HashTool.encode(password));
    }

    public void updateFavPost(Long id, Post post) {
        User user = findUserById(id);
        List<Post> favPosts = user.getFavoritePosts();
        if (favPosts.contains(post)) {
            favPosts.remove(post);
        } else {
            favPosts.add(post);
        }
        user.setFavoritePosts(favPosts);
        userRepository.save(user);
    }

}
