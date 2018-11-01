package com.neuedu.sell.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {

    /* 商品Id */
    private String productId;
    /* 商品数量 */
    private Integer productQuantity;

}
