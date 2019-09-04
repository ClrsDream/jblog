package com.xiaoteng.blog.utils;

import com.xiaoteng.blog.library.jsoup.JBlogWhiteList;
import org.jsoup.Jsoup;

public class Helper {

    public static String clean(String c) {
        return Jsoup.clean(c, JBlogWhiteList.basic());
    }

}
