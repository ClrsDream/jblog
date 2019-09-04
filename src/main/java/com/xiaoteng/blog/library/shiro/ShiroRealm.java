package com.xiaoteng.blog.library.shiro;

import com.xiaoteng.blog.model.User;
import com.xiaoteng.blog.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    public UserService userService;

    {
        // 配置shiro的密码校验规则
        ShiroCredentialsMatcher shiroCredentialsMatcher = new ShiroCredentialsMatcher();
        this.setCredentialsMatcher(shiroCredentialsMatcher);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String email = (String) authenticationToken.getPrincipal();
        User user = this.userService.findUserByEmail(email);
        if (user == null) {
            throw new UnknownAccountException();
        }
        return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
    }
}
