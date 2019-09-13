package com.xiaoteng.blog.utils;

import com.xiaoteng.blog.library.jsoup.JBlogWhiteList;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Component
public class Helper {

    private final static Logger log = LoggerFactory.getLogger(Helper.class);

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

    public static RedirectAttributes getRedirectAttributes(Object[] objects) {
        for (Object o : objects) {
            if (o instanceof RedirectAttributes) {
                return (RedirectAttributes) o;
            }
        }
        return null;
    }

}
