package com.spicis.constants;

import org.springframework.stereotype.Component;

@Component
public class Constants {

    public static final String SLASH = "/";
    public static final String MP4 = ".mp4";
    public static final String PNG = ".png";

    public static final String APP_ID = "appid";
    public static final String SECRET = "secret";
    public static final String GRANT_TYPE = "grant_type";

    public static final String CLIENT_CREDENTIAL = "client_credential";

    /**
     * 获取token名字
     */
    public static final String USER_TOKEN = "Authorization";

    /**
     * 获取ACCESS_TOKEN名字 小程序
     */
    public static final String MINI_PROGRAM_ACCESS_TOKEN = "MIMI_PROGRAM_ACCESS_TOKEN";

    /**
     * 获取sessionId名字
     */
    public static final String SESSION_ID = "SESSION_ID";
    /**
     * 获取token时效时间
     */
    public static final long TOKEN_EX = 7200;

    /**
     * 获取session_key时效时间
     */
    public static final int SESSION_KEY_EX = 7200;

    /**
     * 获取JSCODE2SESSION的url
     */
    public static final String JSCODE2SESSION="https://api.weixin.qq.com/sns/jscode2session";

    /**
     * 上传永久图片素材
     */
    public static final String ADD_MATERIAL = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN&type=TYPE";

    /**
     * 获取小程序码
     */
    public static final String GET_WX_CODE="https://api.weixin.qq.com/wxa/getwxacode";
    public static final String GET_WX_CODE_UNLIMIT="https://api.weixin.qq.com/wxa/getwxacodeunlimit";

    /**
     * 微信公众号发送模板消息
     */
    public static final String templateMessageSend="https://api.weixin.qq.com/cgi-bin/message/template/send";

    /**
     * 小程序发送订阅消息
     */
    public static final String SUBSCRIBE_MESSAGE = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send";

    public static final String ACCESS_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    public static final String UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";

    /**
     * 生成公众号菜单
     */
    public static final String MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    public static final String UNIONID_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

    /**
     *
     */
    public static final String MAP_LOCATION_URL = "https://apis.map.qq.com/ws/geocoder/v1/";

    /**
     * 生成小程序scheme url
     */
    public static final String GENERATE_SCHEME_RUL = "https://api.weixin.qq.com/wxa/generatescheme?access_token=ACCESS_TOKEN";


}
