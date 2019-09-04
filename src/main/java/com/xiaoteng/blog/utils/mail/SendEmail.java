package com.xiaoteng.blog.utils.mail;

import com.aliyuncs.exceptions.ClientException;

public interface SendEmail {

    void send(String email, String subject, String body) throws ClientException;

}
