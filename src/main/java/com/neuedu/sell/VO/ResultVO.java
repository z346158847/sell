package com.neuedu.sell.VO;

import lombok.Data;

@Data
public class ResultVO<T> {

    private Integer code;
    private String message;
    private T data;


}
