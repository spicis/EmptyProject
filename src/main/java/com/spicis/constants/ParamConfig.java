package com.spicis.constants;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class ParamConfig {

    @Value("${param.mini-program.appId}")
    private String miniProgramAppId;

    @Value("${param.mini-program.secretId}")
    private String miniProgramSecretId;

}
