package com.boot.web.sys.security;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.krm.common.constant.Constant;
import com.boot.web.sys.model.SysUser;
import com.boot.web.sys.service.SysMenuService;
import com.boot.web.sys.service.SysRoleService;
import com.boot.web.sys.service.SysUserService;
import com.boot.util.SysUserUtils;

public class ShiroCaseRealm extends AuthorizingRealm {
    private Logger log = LoggerFactory.getLogger(ShiroCaseRealm.class);
    @Autowired
    private SessionDAO sessionDAO;
    @Resource
    private SysMenuService sysMenuService;
    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysRoleService sysRoleService;
    private String casServerUrlPrefix;
    private String casService;

    public String getCasServerUrlPrefix() {
        return casServerUrlPrefix;
    }

    public void setCasServerUrlPrefix(String casServerUrlPrefix) {
        this.casServerUrlPrefix = casServerUrlPrefix;
    }

    public String getCasService() {
        return casService;
    }

    public void setCasService(String casService) {
        this.casService = casService;
    }


    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用,负责在应用程序中决定用户的访问控制的方法
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SysUser sessionUser = SysUserUtils.getSessionLoginUser();
        if (sessionUser != null) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            if (sessionUser.isAdmin()) {
                info.addStringPermission("*");
            } else {
                info.addStringPermission("index");
                info.addStringPermissions(sysRoleService.getUserPermissions());
            }
            return info;
        }
        return null;
    }

    /**
     * 认证回调函数，登录信息和用户验证信息验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
        DefaultWebSessionManager sessionManager = (DefaultWebSessionManager) securityManager.getSessionManager();
        Subject currentUser = SecurityUtils.getSubject();
        //提前1秒去判断   防止这个if没进  等执行下面的时候它却失效了  
//        if((System.currentTimeMillis()-currentUser.getSession().getStartTimestamp().getTime())>=currentUser.getSession().getTimeout()-1000){
//            ThreadContext.remove(ThreadContext.SUBJECT_KEY);//移除线程里面的subject
//            sessionDAO.delete(currentUser.getSession());//移除失效的session
//            currentUser = SecurityUtils.getSubject();//重新获取subject
//        }
        // 踢出已登录的用户
//        Collection<Session> sessions = sessionDAO.getActiveSessions();
//        for (Session session : sessions) {
//            if (token.getUsername().equals(String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY)))) {
//                session.setTimeout(0);// 设置session立即失效，即将其踢出系统
//                break;
//            }
//        }
        //查询用户信息
        SysUser user = new SysUser();
        user.setUsername(token.getUsername());
        SysUser currUser = sysUserService.selectOne(user);
        //用户校验
        if (currUser != null) {
            //currentUser.getSession().setAttribute(Constant.SESSION_LOGIN_USER, currUser);
            if (currentUser.getSession() != null) {
                log.info("Session默认超时时间为[" + currentUser.getSession().getTimeout() + "]毫秒");
            }
            return new SimpleAuthenticationInfo(currUser.getUsername(), currUser.getPassword(), this.getName());
        }
        return null;

    }


    /**
     * 设定密码校验的Hash算法与迭代次数
     */
    @PostConstruct
    public void initCredentialsMatcher() {
        setCredentialsMatcher(new WebCredentialsMatcher());
    }

    private void setSession(Object key, Object value) {
        Subject currentUser = SecurityUtils.getSubject();
        if (null != currentUser) {
            Session session = currentUser.getSession();
            System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");
            if (null != session) {
                session.setAttribute(key, value);
            }
        }
    }


}
