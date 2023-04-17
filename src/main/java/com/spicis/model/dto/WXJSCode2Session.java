package com.spicis.model.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class WXJSCode2Session {
    @JSONField(name = "openid")
    private String openId;
    @JSONField(name = "session_key")
    private String sessionKey;
    @JSONField(name = "unionid")
    private String unionId;
    @JSONField(name = "errcode")
    private String errCode;
    @JSONField(name = "errmsg")
    private String errMsg;
}
