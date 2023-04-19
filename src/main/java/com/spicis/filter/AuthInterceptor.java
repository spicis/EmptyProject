package com.spicis.filter;

import com.alibaba.fastjson.JSON;
import com.spicis.constants.Constants;
import com.spicis.constants.MessageConst;
import com.spicis.logger.LogFactory;
import com.spicis.model.Context;
import com.spicis.model.HttpContext;
import com.spicis.model.TraceLog;
import com.spicis.model.response.BaseBean;
import com.spicis.service.redis.IRedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private IRedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean result = true;

        //构造上下文
        new Context(request, response);

        if ("/error".equals(request.getPathInfo())) {
            return true;
        }
        if (handler instanceof HandlerMethod) {
            AuthIgnore authIgnore = ((HandlerMethod) handler).getMethodAnnotation(AuthIgnore.class);
            if (authIgnore != null) {
                return true;
            } else {
                result = checkLogin(request, response);
            }

        }

        return result;
    }

    private boolean checkLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        boolean result = true;
        String token = request.getHeader(Constants.USER_TOKEN);
        if (StringUtils.isBlank(token)) {
            result = false;
        } else {
            String sessionId = redisService.get(token);
            if (StringUtils.isBlank(sessionId)) {
                result = false;
            } else {
                //取userId
                String[] strArr = sessionId.split("###");
                if (strArr != null && strArr.length == 2) {
                    String userIdStr = strArr[1];
                    try {
                        int userId = Integer.parseInt(userIdStr);

                        ThreadLocal<TraceLog> trace = Context.trace;
                        TraceLog traceLog = trace.get();
                        traceLog.setUserId(userIdStr);

                        ThreadLocal<HttpContext> threadLocal = Context.httpContext;
                        HttpContext httpContext = threadLocal.get();
                        httpContext.setUserId(userId);
                    } catch (NumberFormatException e) {
                        LogFactory.getErrorLogger().logError("getLoginUserId exception", e);
                        result = false;
                    }
                }
            }
        }

        if (!result) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter out = response.getWriter();
            try {
                BaseBean<Object> outBean = new BaseBean(MessageConst.TOKEN_EXPIRE, MessageConst.ILLEGAL_TOKEN, null);
                out.append(JSON.toJSONString(outBean));
                return false;
            } catch (Exception e) {
                response.sendError(500);
                LogFactory.getErrorLogger().logError("AuthInterceptor", e);
                return false;
            }
        }

        redisService.expire(token, Constants.SESSION_KEY_EX);
        return true;
    }
}
