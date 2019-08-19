package com.xiaoteng.blog.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class HashTool {

    // 加密
    public static String encode(String password) {
        PasswordEncoder pe = new BCryptPasswordEncoder();
        return pe.encode(password);
    }

    // 检查密码是否匹配
    public static Boolean check(String password, String hashed) {
        PasswordEncoder pe = new BCryptPasswordEncoder();
        return pe.matches(password, hashed);
    }

}
