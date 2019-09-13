package com.xiaoteng.blog.mails;

import com.aliyuncs.exceptions.ClientException;
import com.xiaoteng.blog.jblog.PasswordFindUtil;
import com.xiaoteng.blog.model.User;
import com.xiaoteng.blog.router.WebRouter;
import com.xiaoteng.blog.utils.mail.SendEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PasswordFindMail {

    private final static Logger log = LoggerFactory.getLogger(PasswordFindMail.class);

    private SendEmail sendEmail;

    private User user;

    private String appUrl;

    public PasswordFindMail(User user) {
        this.user = user;
    }

    public SendEmail getSendEmail() {
        return sendEmail;
    }

    public void setSendEmail(SendEmail sendEmail) {
        this.sendEmail = sendEmail;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public boolean send() {
        // url
        String queryStr = PasswordFindUtil.query(user);
        String url = appUrl + WebRouter.PASSWORD_RESET + "?" + queryStr;
        log.info("passwordFindUrl:{}", url);
        String subject = "密码找回";
        String content = "密码找回链接：<a href=\"" + url + "\">" + url + "</a>";
        try {
            sendEmail.send(user.getEmail(), subject, content);
            return true;
        } catch (ClientException e) {
            e.printStackTrace();
            return false;
        }
    }
}
