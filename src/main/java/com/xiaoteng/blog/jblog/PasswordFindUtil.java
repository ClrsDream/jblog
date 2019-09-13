package com.xiaoteng.blog.jblog;

import com.xiaoteng.blog.model.User;
import com.xiaoteng.blog.utils.Helper;

import java.util.Date;

public class PasswordFindUtil {

    // 超时时间，单位：毫秒
    public final static int TIMEOUT = 3600000;

    public static boolean isTimeout(Long t) {
        long now = (new Date()).getTime();
        return now - TIMEOUT > t;
    }

    public static String query(User user) {
        Long t = (new Date()).getTime();
        String sign = sign(user, t);
        return "timestamp=" + t + "&sign=" + sign + "&email=" + user.getEmail();
    }

    public static boolean verify(User user, Long t, String sign) {
        String s = sign(user, t);
        return s.equals(sign);
    }

    public static String sign(User user, Long t) {
        String preSign = t + user.getEmail() + user.getPassword();
        return Helper.md5(preSign);
    }

}
