package com.spicis.utils;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.spicis.constants.Constants;
import com.spicis.constants.ParamConfig;
import com.spicis.logger.LogFactory;
import com.spicis.model.dto.WXJSCode2Session;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class WXUtils {
    public final static String ENV_PRD = "prd";
    public final static String ENV_DEV = "dev";
    public final static String ENV_QA = "qa";
    @Value("${spring.profiles.active}")
    private String env;
    @Autowired
    private ParamConfig paramConfig;

    public WXJSCode2Session getWXSession(String code) {
        WXJSCode2Session wxjsCode2Session = new WXJSCode2Session();
        try {
            if (StringUtils.equals(ENV_PRD, env) || StringUtils.equals(ENV_QA, env)) {
                Map<String, Object> params = new HashMap<>();
                params.put(Constants.APP_ID, paramConfig.getMiniProgramAppId());
                params.put(Constants.SECRET, paramConfig.getMiniProgramSecretId());
                params.put(Constants.JS_CODE, code);
                params.put(Constants.GRANT_TYPE, Constants.AUTHORIZATION_CODE);

                String data = HttpUtil.get(Constants.JSCODE2SESSION, params);
                wxjsCode2Session = JSONObject.parseObject(data, WXJSCode2Session.class);
                String openId = wxjsCode2Session.getOpenId();
                LogFactory.getInfoLogger().logInfo(String.format("用户登录【%s】： %s", openId, wxjsCode2Session));
            } else if(StringUtils.equals(ENV_DEV, env)) {
                wxjsCode2Session.setOpenId("testOpenId");
                wxjsCode2Session.setSessionKey("session_key");
                wxjsCode2Session.setUnionId("");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogFactory.getErrorLogger().logError("微信登录失败");
        }
        return wxjsCode2Session;
    }
}
