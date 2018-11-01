package com.neuedu.sell.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum {

    NEW(0,"新下单"),
    FINISHED(1,"已完成"),
    CANCEL(2,"已取消");


    private Integer code;
    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
