package com.bj.zzq.config;


import com.bj.zzq.model.AdminEntity;
import com.bj.zzq.service.AdminService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class UserShiroRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger(UserShiroRealm.class);

    @Autowired
    private AdminService adminService;

    /**
     * 校验权限时，只有登陆认证后的请求才会走这个方法
     *
     * @param principalCollection
     * @return
     */
    @Override
    public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("##################执行Shiro权限认证##################");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        info.addRole("admin");
        // 返回null的话，就会导致任何用户访问被拦截的请求时，都会自动跳转到unauthorizedUrl指定的地址
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token= (UsernamePasswordToken) authenticationToken;
        AdminEntity adminEntity = adminService.selectAdminByUsername(token.getUsername());
        // 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
        return new SimpleAuthenticationInfo(adminEntity.getUsername(), adminEntity.getPassword(), getName());
    }
}
