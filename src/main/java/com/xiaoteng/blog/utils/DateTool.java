package com.xiaoteng.blog.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTool {

    // 字符串转Date对象
    public static Date str2Date(String d) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        date = sdf.parse(d);
        return date;
    }

}
