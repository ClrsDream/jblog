package com.xiaoteng.blog.library.shiro;

import com.xiaoteng.blog.utils.HashTool;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShiroCredentialsMatcher implements CredentialsMatcher {

    private static final Logger log = LoggerFactory.getLogger(ShiroCredentialsMatcher.class);

    @Override
    public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
        log.info("authenticationToken{},authenticationInfo{}", authenticationToken, authenticationInfo);
        String password = String.valueOf(authenticationToken.getCredentials());
        String hashedPassword = String.valueOf(authenticationInfo.getCredentials());
        log.info("password{},hashedPassword{}", password, hashedPassword);
        return HashTool.check(password, hashedPassword);
    }
}
