package com.spicis.model.request;

import com.spicis.constants.MessageConst;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginWXReq {

    @NotNull(message = MessageConst.PARAMS_ERROR)
    private String code;
}
