package com.spicis.service;

import com.spicis.constants.Constants;
import com.spicis.constants.MessageConst;
import com.spicis.logger.LogFactory;
import com.spicis.model.Context;
import com.spicis.model.response.BaseBean;
import com.spicis.service.redis.IRedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class BaseService {

    @Autowired
    private IRedisService redisService;

    public BaseBean errorBean(int status, String msg) {
        return new BaseBean(status, msg, null);
    }

    public BaseBean errorBean(String msg) {
        return new BaseBean(MessageConst.FAIL_WITH_MSG, msg, null);
    }

    public BaseBean errorBean() {
        return new BaseBean(MessageConst.FAIL_WITH_MSG, MessageConst.FAIL_MESSAGE, null);
    }

    public <T> BaseBean<T> successBean(int status, String msg, T result) {
        return new BaseBean(status, msg, result);
    }

    public <T> BaseBean<T> successBean(T result) {
        return new BaseBean(MessageConst.SUCCESS, MessageConst.SUCCESS_MESSAGE, result);
    }

    public <T> BaseBean<T> successBean() {
        return new BaseBean(MessageConst.SUCCESS, MessageConst.SUCCESS_MESSAGE, null);
    }

    public Integer getLoginUserId(Context context) {
        try {
            HttpServletRequest request = context.getRequest();
            String token = request.getHeader(Constants.USER_TOKEN);
            if (StringUtils.isNotBlank(token)) {
                String sessionId = redisService.get(token);
                if (StringUtils.isNotBlank(sessionId)) {
                    String[] strArr = sessionId.split("###");
                    if (strArr != null && strArr.length == 2) {
                        String userIdStr = strArr[1];
                        return Integer.parseInt(userIdStr);
                    }
                }
            }
        } catch (Exception e) {
            LogFactory.getErrorLogger().logError("getLoginUserId exception", e);
        }
        return null;
    }
}
