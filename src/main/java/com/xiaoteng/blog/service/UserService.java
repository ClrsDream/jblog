package com.xiaoteng.blog.service;

import com.xiaoteng.blog.enums.UserStatusEnum;
import com.xiaoteng.blog.mappers.UserMapper;
import com.xiaoteng.blog.model.Post;
import com.xiaoteng.blog.model.User;
import com.xiaoteng.blog.utils.HashTool;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final static Logger log = LoggerFactory.getLogger(UserService.class);

    @Value("${blog.user.defaultAvatar}")
    private String defaultAvatar;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PostService postService;

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

    public boolean userFavPost(Long userId, Long postId) {
        Long exists = userMapper.findPostFav(userId, postId);
        return exists != null;
    }

    public void updateFavPost(Long id, Post post) {
        User user = findUserById(id);
        // 判断是否存在记录
        boolean res = userFavPost(user.getId(), post.getId());
        if (!res) {
            // 记录不存在，那么就创建
            log.info("创建帖子[{}]的喜欢记录", post.getId());
            userMapper.createPostFav(user.getId(), post.getId());
            postService.userFavCountInc(post.getId());
        } else {
            log.info("取消帖子[{}]的喜欢记录", post.getId());
            userMapper.cancelPostFav(user.getId(), post.getId());
            postService.userFavCountDec(post.getId());
        }
    }

    public List<User> selectFavUsers(Long postId) {
        return userMapper.selectFavUsers(postId);
    }

    public List<User> paginate(User user, int offset, int pageSize) {
        return userMapper.paginate(user, offset, pageSize);
    }

    public Long count(User user) {
        return userMapper.count(user);
    }

    public void update(User user, Long id) {
        userMapper.update(user, id);
    }

}
