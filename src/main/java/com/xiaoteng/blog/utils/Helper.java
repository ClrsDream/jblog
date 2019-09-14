package com.xiaoteng.blog.utils;

import com.xiaoteng.blog.library.jsoup.JBlogWhiteList;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigInteger;
import java.security.MessageDigest;

@Component
public class Helper {

    private final static Logger log = LoggerFactory.getLogger(Helper.class);

    public static String clean(String c) {
        return Jsoup.clean(c, JBlogWhiteList.basic());
    }

    public static String sub(String c) {
        if (c == null) {
            return "";
        }
        // 先过滤html字符
        c = Jsoup.clean(c, Whitelist.none());
        if (c.length() <= 120) {
            return c;
        }
        // 截取120个字符
        return c.substring(0, 120);
    }

    public static RedirectAttributes getRedirectAttributes(Object[] objects) {
        for (Object o : objects) {
            if (o instanceof RedirectAttributes) {
                return (RedirectAttributes) o;
            }
        }
        return null;
    }

    public static String md5(String c_password) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 调用update方法计算MD5函数(参数：将密码串转换为操作系统的字节编码)
            md.update(c_password.getBytes());
            // digest()最后返回md5的hash值，返回值为8位的字符串，但此方法要先调用update
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值,数值从1开始
            // BigInteger会把0省略掉，需补全至32位，重写一个方法将16位数转换为32位数
            String md5 = new BigInteger(1, md.digest()).toString(16);
            return fillMD5(md5);
        } catch (Exception e) {
            throw new RuntimeException("MD5加密错误：" + e.getMessage(), e);
        }
    }

    // 将16位数转为32位
    public static String fillMD5(String md5) {
        return md5.length() == 32 ? md5 : fillMD5("0" + md5);
    }

}
