package com.neuedu.sell.utils;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

@Data
public class WeixinResult {

    private String access_token;
    private String expires_in;
    private String refresh_token;
    private String openid;
    private String scope;
}
