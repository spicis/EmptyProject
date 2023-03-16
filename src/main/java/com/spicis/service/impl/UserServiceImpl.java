package com.spicis.service.impl;

import com.spicis.constants.ParamConfig;
import com.spicis.model.Context;
import com.spicis.model.request.LoginWXReq;
import com.spicis.model.response.BaseBean;
import com.spicis.model.response.LoginWXResp;
import com.spicis.service.BaseService;
import com.spicis.service.IUserService;
import com.spicis.service.redis.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends BaseService implements IUserService {

    @Autowired
    private ParamConfig paramConfig;

    @Autowired
    private IRedisService redisService;

    @Override
    public BaseBean loginWX(LoginWXReq inBean, Context context) {
        try {
            String code = inBean.getCode();

            LoginWXResp resp = new LoginWXResp();
            return successBean(resp);
        } catch (Exception e) {
            return errorBean();
        }
    }

    @Override
    public BaseBean loadUserInfo(LoginWXReq inBean, Context context) {
        Integer userId = getLoginUserId(context);

        LoginWXResp resp = new LoginWXResp();
        return successBean(resp);
    }
}
