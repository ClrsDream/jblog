package com.xiaoteng.blog.jobs;

import com.xiaoteng.blog.utils.BeanUtil;
import com.xiaoteng.blog.utils.mail.AliyunMail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

public class SendWelcomeEmailJob implements Runnable {

    private final Logger log = LoggerFactory.getLogger(SendWelcomeEmailJob.class);

    private String email;

    public SendWelcomeEmailJob(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void run() {
        log.info("{}", "send welcome email running.");
        ApplicationContext applicationContext = BeanUtil.getApplicationContext();
        AliyunMail aliyunMail = (AliyunMail) applicationContext.getBean(AliyunMail.class);
        aliyunMail.send(this.email, "欢迎您注册成为本站会员", "您好。");
    }
}
