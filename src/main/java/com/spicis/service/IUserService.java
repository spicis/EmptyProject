package com.spicis.service;

import com.spicis.model.Context;
import com.spicis.model.request.LoginWXReq;
import com.spicis.model.response.BaseBean;

public interface IUserService {

    BaseBean loginWX(LoginWXReq inBean, Context context);

    BaseBean loadUserInfo(LoginWXReq inBean, Context context);
}
