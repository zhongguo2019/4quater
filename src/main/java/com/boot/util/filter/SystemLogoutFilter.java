package com.boot.util.filter;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.session.SessionException;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.krm.common.constant.Constant;
import com.boot.util.SysUserUtils;

/**
 * 重写shiro退出过滤器，原生过滤器退出前并未清空缓存中的session信息
 *
 * @author cat
 */
public class SystemLogoutFilter extends LogoutFilter {
    protected Logger logger = LoggerFactory.getLogger(SystemLogoutFilter.class);
    @Autowired
    private SessionDAO sessionDAO;

    protected boolean preHandle(HttpServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        String redirectUrl = getRedirectUrl(request, response, subject);
        try {
            // 清空缓存
            if (SysUserUtils.getSessionLoginUser() != null) {
                SysUserUtils.clearCacheUser(SysUserUtils.getSessionLoginUser().getId());
                SysUserUtils.getSession().removeAttribute(Constant.SESSION_LOGIN_USER);
                SysUserUtils.getHttpSession().removeAttribute(Constant.SESSION_LOGIN_USER);
            }
            request.getSession().invalidate();
            logger.info("退出清除session");
        } catch (SessionException e) {
            e.printStackTrace();
        }
        issueRedirect(request, response, redirectUrl);
        return false;

    }

}
