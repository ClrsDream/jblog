package com.xiaoteng.blog.library.jsoup;

import org.jsoup.safety.Whitelist;

public class JBlogWhiteList {

    public static Whitelist basic() {
        Whitelist whitelist = Whitelist.relaxed();
        whitelist.addTags("iframe");
        return whitelist;
    }

}
