package com.spicis.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Calendar;

public class JWTUtils {

    public static String generateToken(String value) {
        Calendar instance = Calendar.getInstance();
        //默认令牌过期时间7天
        instance.add(Calendar.DATE, 7);

        JWTCreator.Builder builder = JWT.create();

        return builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(value));
    }

}