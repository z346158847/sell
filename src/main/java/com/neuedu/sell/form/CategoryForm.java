package com.neuedu.sell.form;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
public class CategoryForm {

    private Integer categoryId;
    /*类别名字*/
    private String categoryName;
    /*类别编号*/
    private Integer categoryType;
}