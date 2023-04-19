package com.spicis.model;

import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson.JSON;
import lombok.Data;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Data
public class Context {

    public static ThreadLocal<TraceLog> trace = new ThreadLocal<>();
    public static ThreadLocal<HttpContext> httpContext = new ThreadLocal<>();

    public Context(HttpServletRequest request, HttpServletResponse response) {
        String url = request.getRequestURL().toString();
        String method = request.getMethod();

        TraceLog traceLog = new TraceLog();
        traceLog.setRequestId(UUID.randomUUID().toString());
        traceLog.setUrl(url);
        traceLog.setMethod(method);
        traceLog.begin();
        trace.set(traceLog);

        HttpContext http = new HttpContext(request, response);
        httpContext.set(http);
    }
}
