package com.spicis.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.spicis.logger.LogFactory;
import com.spicis.utils.DateUtils;
import lombok.Data;

import java.util.Date;

@Data
public class TraceLog {
    private String userId;
    private String requestId;
    private String method;
    private String startTime;
    private String endTime;
    private long begin;
    private long end;
    private long consumeTime;
    private String request;
    private String response;
    private String url;
    @JSONField(serialize = false)
    private TraceLog innerTrace;

    public void begin() {
        Date date = DateUtils.nowDate();
        this.startTime = DateUtils.formatDateTime(date, "YYYY-MM-dd HH:mm:ss:SSS");
        this.begin = date.getTime();
    }

    public void end() {
        Date date = DateUtils.nowDate();
        this.endTime = DateUtils.formatDateTime(date, "YYYY-MM-dd HH:mm:ss:SSS");
        this.end = date.getTime();
        this.consumeTime = end - this.begin;
        LogFactory.getTraceLogger().logInfo(JSON.toJSONString(this));
    }
}
