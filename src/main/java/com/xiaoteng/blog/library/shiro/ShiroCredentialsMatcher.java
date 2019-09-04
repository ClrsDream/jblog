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
        String password = String.valueOf((char[]) authenticationToken.getCredentials());
        String hashedPassword = String.valueOf(authenticationInfo.getCredentials());
        return HashTool.check(password, hashedPassword);
    }
}
