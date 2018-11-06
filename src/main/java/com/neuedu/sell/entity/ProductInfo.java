package com.neuedu.sell.entity;

import com.neuedu.sell.enums.ProductStatusEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
public class ProductInfo {

    @Id
    private String productId;
    /* 商品名 */
    private String productName;
    /* 商品单价 */
    private BigDecimal productPrice;
    /* 库存 */
    private Integer productStock;
    /* 商品描述 */
    private String productDescription;
    /* 商品小图 */
    private String productIcon;
    /* 商品状态，0正常，1下架 */
    private Integer productStatus = ProductStatusEnum.UP.getCode();
    /* 类目编号 */
    private Integer categoryType;

}
