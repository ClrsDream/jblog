package com.xiaoteng.blog.utils;

import com.xiaoteng.blog.library.jsoup.JBlogWhiteList;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

public class Helper {

    public static String clean(String c) {
        return Jsoup.clean(c, JBlogWhiteList.basic());
    }

    public static String sub(String c) {
        // 先过滤html字符
        c = Jsoup.clean(c, Whitelist.none());
        if (c.length() <= 120) {
            return c;
        }
        // 截取120个字符
        return c.substring(0, 120);
    }

}
