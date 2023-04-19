package com.spicis.service.impl;

import com.spicis.constants.Constants;
import com.spicis.constants.ParamConfig;
import com.spicis.model.Context;
import com.spicis.model.HttpContext;
import com.spicis.model.request.LoginWXReq;
import com.spicis.model.response.BaseBean;
import com.spicis.model.response.LoginWXResp;
import com.spicis.service.BaseService;
import com.spicis.service.IUserService;
import com.spicis.service.redis.IRedisService;
import com.spicis.utils.JWTUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


@Service
public class UserServiceImpl extends BaseService implements IUserService {

    @Autowired
    private ParamConfig paramConfig;

    @Autowired
    private IRedisService redisService;

    @Override
    public BaseBean loginWX(LoginWXReq inBean, HttpContext context) {
        try {
            String code = inBean.getCode();

            HttpServletRequest request = context.getRequest();

            String openId = "test";
            String sessionKey = "sessionKey";

            String userAgent = request.getHeader("user-agent");
            String token = JWTUtils.generateToken(userAgent + sessionKey);
            String sessionId = sessionKey + "###" + 1;
            redisService.set(token, sessionId);
            redisService.setnxWithExpire(token, Constants.SESSION_KEY_EX);

            LoginWXResp resp = new LoginWXResp();
            return successBean(resp);
        } catch (Exception e) {
            return errorBean();
        }
    }

    @Override
    public BaseBean loadUserInfo(LoginWXReq inBean, HttpContext context) {
        Integer userId = getLoginUserId(context);

        LoginWXResp resp = new LoginWXResp();
        return successBean(resp);
    }
}
