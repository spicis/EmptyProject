package com.spicis.controller;

import com.spicis.filter.AuthIgnore;
import com.spicis.logger.LogFactory;
import com.spicis.model.Context;
import com.spicis.model.request.LoginWXReq;
import com.spicis.model.response.BaseBean;
import com.spicis.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static com.spicis.constants.MessageConst.FAIL_MESSAGE;
import static com.spicis.constants.MessageConst.FAIL_WITH_MSG;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @AuthIgnore
    @RequestMapping("/loginwx")
    @ResponseBody
    public BaseBean loginWX(@RequestBody LoginWXReq inBean,
                            HttpServletRequest request, HttpServletResponse response) {
        try {
            Context context = new Context(request, response);
            return userService.loginWX(inBean, context);
        } catch (Exception e) {
            LogFactory.getErrorLogger().logError(FAIL_MESSAGE, e);
        }

        return new BaseBean(FAIL_WITH_MSG, FAIL_MESSAGE, null);
    }

    @RequestMapping("/loaduserinfo")
    @ResponseBody
    public BaseBean loadUserInfo(@RequestBody @Valid LoginWXReq inBean, BindingResult result,
                                 HttpServletRequest request, HttpServletResponse response) {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return new BaseBean(FAIL_WITH_MSG, error.getDefaultMessage(), null);
            }
        }
        try {
            Context context = new Context(request, response);
            return userService.loadUserInfo(inBean, context);
        } catch (Exception e) {
            LogFactory.getErrorLogger().logError(FAIL_MESSAGE, e);
        }

        return new BaseBean(FAIL_WITH_MSG, FAIL_MESSAGE, null);
    }

}
