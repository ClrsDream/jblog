package com.xiaoteng.blog.utils.mail;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public final class AliyunMail implements SendEmail {

    private final Logger log = LoggerFactory.getLogger(AliyunMail.class);

    @Value("${blog.services.aliyun.email.access_key}")
    private String accessKey;

    @Value("${blog.services.aliyun.email.access_secret}")
    private String accessSecret;

    @Value("${blog.services.aliyun.email.from}")
    private String fromAddress;

    @Value("${blog.services.aliyun.email.area}")
    private String area;

    @Value("${blog.services.aliyun.email.account}")
    private String account;

    @Override
    public void send(String email, String subject, String body) {
        try {
            SingleSendMailRequest request = new SingleSendMailRequest();
            request.setAccountName(account);
            request.setAddressType(1);
            request.setReplyToAddress(true);
            request.setToAddress(email);
            request.setSubject(subject);
            request.setHtmlBody(body);

            SingleSendMailResponse response = getClient().getAcsResponse(request);

            // 发送成功
            log.info("{}", response);
        } catch (ClientException se) {
            se.printStackTrace();
        }
    }

    private IAcsClient getClient() {
        IClientProfile profile = DefaultProfile.getProfile(this.area, this.accessKey, this.accessSecret);
        return new DefaultAcsClient(profile);
    }

}
