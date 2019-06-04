package com.bj.zzq.config;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

/**
 * 自定义密码校验器
 */
public class UserCredentialsMatcher extends HashedCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) throws ExcessiveAttemptsException {
        //验证密码是否通过
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String passwordFromToken = new String(usernamePasswordToken.getPassword());
        Object credentialsFromDb = info.getCredentials();
        if (StringUtils.isBlank(usernamePasswordToken.getUsername()) || StringUtils.isBlank(new String(usernamePasswordToken.getPassword())) || !passwordFromToken.equals(credentialsFromDb)) {
            throw new IncorrectCredentialsException("用户名密码错误");
        }
        return true;
    }
}
