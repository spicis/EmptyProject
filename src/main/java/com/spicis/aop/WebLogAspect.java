package com.spicis.aop;

import com.alibaba.fastjson.JSON;
import com.spicis.logger.LogFactory;
import com.spicis.model.Context;
import com.spicis.model.TraceLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Aspect
@Component
public class WebLogAspect {

    @Pointcut("execution(public * com.spicis.controller..*.*(..))")
    public void webLog() {

    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            String params = "{}";
            for (int i = 0; i < args.length; i++) {
                Object arg = args[i];
                if (arg instanceof HttpServletRequest || arg instanceof HttpServletResponse
                        || arg instanceof BindingResult) {

                } else {
                    params = JSON.toJSONString(arg);
                }
            }
            ThreadLocal<TraceLog> trace = Context.trace;
            TraceLog traceLog = trace.get();
            traceLog.setRequest(params);
        } catch (Exception e) {
            LogFactory.getErrorLogger().logError("doBefore exception ", e);
        }
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        try {
            // 处理完请求，返回内容
            ThreadLocal<TraceLog> trace = Context.trace;
            TraceLog traceLog = trace.get();
            traceLog.setResponse(JSON.toJSONString(ret));
            traceLog.end();
        } catch (Exception e) {
            LogFactory.getErrorLogger().logError("doAfterReturning exception ", e);
        } finally {
            Context.trace.remove();
            Context.httpContext.remove();
        }
    }

}
