package com.neuedu.sell.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * 订单商品
 */
@Data
@Entity
public class OrderDetail {

    @Id
    private String detailId;
    /* 订单Id */
    private String orderId;
    /* 商品Id */
    private String productId;
    /* 商品名 */
    private String productName;
    /* 单价 */
    private BigDecimal productPrice;
    /* 数量 */
    private Integer productQuantity;
    /* 商品小图 */
    private String productIcon;

    public OrderDetail() {
    }

    public OrderDetail(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
