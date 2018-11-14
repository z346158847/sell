package com.neuedu.sell.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.neuedu.sell.utils.serializer.Date2langSerializer;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 商品类别实体类
 */
@Data
@Entity
public class ProductCategory {

    @Id
    @GeneratedValue
    private Integer categoryId;

    /*类别名字*/
    private String categoryName;
    /*类别编号*/
    private Integer categoryType;
    /*创建时间*/
    @JsonSerialize(using = Date2langSerializer.class)
    private Date createTime;
    /*修改时间*/
    @JsonSerialize(using = Date2langSerializer.class)
    private Date updateTime;

}
