package com.xiaoteng.blog.utils;

import com.youbenzi.mdtool.tool.MDTool;

public class Markdown {

    public String toHTML(String c) {
        return MDTool.markdown2Html(c);
    }

}
