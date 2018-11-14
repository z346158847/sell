package com.neuedu.sell.form;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.neuedu.sell.enums.ProductStatusEnum;
import com.neuedu.sell.utils.serializer.Date2langSerializer;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProductForm {

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
    /* 类目编号 */
    private Integer categoryType;
}
