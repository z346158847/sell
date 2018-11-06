package com.neuedu.sell.utils;

import java.util.Random;

public class KeyUtils {

    /**
     * 随机数加 时间
     * @return
     */
    public static synchronized String generateUniqueKey(){

        Random random = new Random();
        int number =  random.nextInt(900000) + 100000;

        return String.valueOf(System.currentTimeMillis()) + number;

    }
}
