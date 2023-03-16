package com.spicis.model.response;

import lombok.Data;

@Data
public class LoginWXResp {

    private String openId;

    private String token;
}
