package com.neuedu.sell.utils;

import com.neuedu.sell.enums.CodeEnum;
import com.neuedu.sell.enums.OrderStatusEnum;

public class EnumUtil {

    //根据code的值获得枚举对象
    public static <T extends CodeEnum> T getEnumByCode(Integer code, Class<T> enumClass){
        //反射该枚举类的所有枚举实例
        for (T each : enumClass.getEnumConstants()) {
            //利用一个接口提供getCode方法
            if (code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }
}
