package com.xiaoteng.blog.router;

public class WebRouter {

    public final static String INDEX = "/";
    public final static String INDEX_PARAM = "/t/{type}";
    // 登录
    public final static String LOGIN = "/login";
    // 安全退出
    public final static String LOGOUT = "/logout";
    // 注册界面
    public final static String REGISTER = "/register";
    // 密码找回
    public final static String PASSWORD_FOUND = "/password/find";
    // 密码重置
    public final static String PASSWORD_RESET = "/password/reset";
    // 会员首页
    public final static String HOME = "/home";
    // 资料编辑页面
    public final static String HOME_PROFILE = "/home/profile";
    // 修改密码
    public final static String HOME_CHANGE_PASSWORD = "/home/changePassword";
    // 帖子创建
    public final static String POST_CREATE = "/post/create/index";
    // 帖子详情页
    public final static String POST_DETAIL = "/post/{id}";
    // 上传图片
    public final static String UPLOAD_IMAGE = "/upload/image";
    // 会员主页
    public final static String MEMBER_DETAIL = "/member/{id}";
    // 分类
    public final static String TAG_INDEX = "/tags";
    // 分类详情
    public final static String TAG_DETAIL = "/tag/{id}";
    // 喜欢文章
    public final static String POST_FAVORITE = "/home/post/favorite";
    // 我的喜欢
    public final static String HOME_FAVORITE = "/home/favorite";
    // 图片验证码
    public final static String CAPTCHA_IMAGE = "/captcha/image";
    // 帖子评论
    public final static String POST_COMMENT_CREATE = "/post/{id}/comment";
}
