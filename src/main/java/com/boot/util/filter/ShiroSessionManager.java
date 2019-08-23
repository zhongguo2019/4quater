package com.boot.util.filter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.boot.util.CookieUtils;

@Component
public class ShiroSessionManager extends DefaultWebSessionManager {
    protected Logger logger = LoggerFactory.getLogger(ShiroSessionManager.class);
    // 自定义缓存，存储 客户端-sessionid
    public static final Map<String, Serializable> MAP = new HashMap<String, Serializable>();
    private static Integer sessionCookieTime = -1;// sessionId的cookie存活时间。单位为S
    //private static Long globalSessionTimeout=0;// sessionId的cookie存活时间。单位为S
    private static Long sessionTime_cs = 180000L;// session的过期时间C/S客户端。单位为MS

    public ShiroSessionManager() {
        super();
    }


    /**
     * 根据客户端的sessionIdKey获取真正的sessionId
     */
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String sessionIdKey = getSeesionIdKey(req);
        Serializable sessionId = MAP.get(sessionIdKey);
        if (sessionId != null) {
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE,
                    ShiroHttpServletRequest.COOKIE_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sessionId);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            Session session = getSession(new WebSessionKey(sessionId, request, response));
            String token = request.getParameter("access_token");
            if (token != null) {
                session.setTimeout(sessionTime_cs);// 设置C/S的session过期时间
            }
        }
        return sessionId;
    }

    /**
     * 创建一个session
     */
    @Override
    protected void onStart(Session session, SessionContext context) {
        // 判断是否是http请求
        if (!WebUtils.isHttp(context)) {
            logger.debug("HTTP请求才能创建session");
            return;
        }
        HttpServletRequest request = WebUtils.getHttpRequest(context);
        HttpServletResponse response = WebUtils.getHttpResponse(context);
        request.removeAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE);
        request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_IS_NEW, Boolean.TRUE);
        String sessionId = UUID.randomUUID().toString().trim().toUpperCase();
        Serializable id = session.getId();
        setSessionIdKeyCookie(request, response, sessionId, sessionCookieTime);// 设置cookie过期时间
        MAP.put(sessionId, id);// 存储sessionIdKey和真正的sessionId
    }


    /**
     * 获取客户端存储的sessionIdKey
     *
     * @param request
     * @return
     */
    private String getSeesionIdKey(HttpServletRequest request) {
        String sessionIdKey = null;
        try {
            sessionIdKey = request.getParameter("access_token");
            if (null == sessionIdKey || sessionIdKey.isEmpty()) {
                sessionIdKey = request.getHeader("access_token");
                if (null == sessionIdKey || sessionIdKey.equals("")) {
                    sessionIdKey = CookieUtils.getCookie(request, "SID");
                }
            }
        } catch (Exception e) {
            logger.debug("获取sessionIdKey失败");
        }
        return sessionIdKey;
    }

    /**
     * 设置sessionIdKey的cookie
     *
     * @param sessionId sessionId
     * @param age       age
     */
    private void setSessionIdKeyCookie(HttpServletRequest request, HttpServletResponse response, String sessionId, Integer age) {
        Cookie cookie = new Cookie("SID", sessionId.toUpperCase());
        cookie.setHttpOnly(Boolean.TRUE);
        cookie.setPath("/");
        cookie.setMaxAge(sessionCookieTime);
        response.addCookie(cookie);
    }
}
