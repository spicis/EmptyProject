package com.spicis.aop;

import com.alibaba.fastjson.JSON;
import com.spicis.logger.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class WebLogAspect {

    @Pointcut("execution(public * com.meihua.controller..*.*(..))")
    public void webLog() {

    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            LogFactory.getInfoLogger().logInfo(request.getRequestURL().toString() + Arrays.toString(joinPoint.getArgs()));

        } catch (Exception e) {
            LogFactory.getErrorLogger().logError("doBefore exception ", e);
        }
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        try {
        // 处理完请求，返回内容
            LogFactory.getInfoLogger().logInfo(JSON.toJSONString(ret));
        } catch (Exception e) {
            LogFactory.getErrorLogger().logError("doAfterReturning exception ", e);
        }
    }

}
