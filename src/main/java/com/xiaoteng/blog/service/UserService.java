package com.xiaoteng.blog.service;

import com.xiaoteng.blog.model.User;
import com.xiaoteng.blog.repositories.UserRepository;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    public User findUserByEmail(String email) {
        Optional<User> optionalUser = this.userRepository.findByEmail(email);
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

}
