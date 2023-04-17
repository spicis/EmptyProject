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

@Aspect
@Component
public class MysqlLogAspect {

    @Pointcut("execution(public * com.spicis.dao..*(..))")
    public void mysqlLog() {

    }

    @Before("mysqlLog()")
    public void doBefore(JoinPoint joinPoint) {
        String params = JSON.toJSONString(joinPoint.getArgs());
        String method = joinPoint.getSignature().getName();

        ThreadLocal<TraceLog> trace = Context.trace;
        TraceLog contextTrace = trace.get();
        String requestId = contextTrace.getRequestId();

        TraceLog traceLog = new TraceLog();
        traceLog.setRequestId(requestId);
        traceLog.setMethod("Mysql");
        traceLog.setUrl(method);
        traceLog.setParams(params);
        traceLog.begin();

        contextTrace.setInnerTrace(traceLog);
    }

    @AfterReturning(returning = "ret", pointcut = "mysqlLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        try {
            // 处理完请求，返回内容
            ThreadLocal<TraceLog> trace = Context.trace;
            TraceLog contextTrace = trace.get();
            TraceLog traceLog = contextTrace.getInnerTrace();

            traceLog.setResponse(JSON.toJSONString(ret));
            traceLog.end();
        } catch (Exception e) {
            LogFactory.getErrorLogger().logError("doAfterReturning exception ", e);
        }
    }

}
