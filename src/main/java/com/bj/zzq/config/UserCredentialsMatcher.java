package com.bj.zzq.config;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

/**
 * 自定义密码校验器
 */
public class UserCredentialsMatcher extends HashedCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) throws ExcessiveAttemptsException {
        //验证密码是否通过
        String credentialsFromToken = new String((char[]) token.getCredentials());
        Object credentialsFromDb = info.getCredentials();
        boolean matches = credentialsFromDb != null && credentialsFromToken != null && credentialsFromToken.equals(credentialsFromDb);
        if (!matches) {
            // 登陆失败，清除认证信息缓存
            throw new IncorrectCredentialsException("密码错误");
        }
        return true;
    }
}
