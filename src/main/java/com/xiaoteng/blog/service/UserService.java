package com.xiaoteng.blog.service;

import com.xiaoteng.blog.enums.UserStatusEnum;
import com.xiaoteng.blog.model.Post;
import com.xiaoteng.blog.model.User;
import com.xiaoteng.blog.repositories.UserRepository;
import com.xiaoteng.blog.utils.HashTool;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Value("${blog.user.defaultAvatar}")
    private String defaultAvatar;

    public User findUserByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        return optionalUser.orElse(null);
    }

    public User findUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    public User findUserByNickname(String nickname) {
        Optional<User> optionalUser = userRepository.findByNickname(nickname);
        return optionalUser.orElse(null);
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
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setNickname(user.getNickname());
        newUser.setPassword(HashTool.encode(user.getPassword()));
        newUser.setStatus(UserStatusEnum.NORMAL.getStatus());
        newUser.setAvatar(defaultAvatar);
        newUser.setIntro("");
        newUser.setQq("");
        newUser.setGithub("");
        newUser.setWeibo("");
        userRepository.save(newUser);

        return newUser;
    }

    public void setPassword(User user, String password) {
        user.setPassword(HashTool.encode(password));
        userRepository.save(user);
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
