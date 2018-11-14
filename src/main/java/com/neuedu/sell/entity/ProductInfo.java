package com.neuedu.sell.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.neuedu.sell.enums.CodeEnum;
import com.neuedu.sell.enums.ProductStatusEnum;
import com.neuedu.sell.utils.EnumUtil;
import com.neuedu.sell.utils.serializer.Date2langSerializer;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
public class ProductInfo  {

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
    /* 创建时间 */
    @JsonSerialize(using = Date2langSerializer.class)
    private Date createTime;
    /* 修改时间 */
    @JsonSerialize(using = Date2langSerializer.class)
    private Date updateTime;

    public ProductStatusEnum getProductStatusEnum(){

        return EnumUtil.getEnumByCode(productStatus, ProductStatusEnum.class);
    }






}
