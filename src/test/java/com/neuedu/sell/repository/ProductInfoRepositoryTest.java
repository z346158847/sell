package com.neuedu.sell.repository;

import com.neuedu.sell.entity.ProductInfo;
import com.neuedu.sell.enums.ProductStatusEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository productInfoRepository;


    @Test
    public void saveTest(){

        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("1111");
        productInfo.setProductName("冰粥");
        productInfo.setCategoryType(1);
        productInfo.setProductDescription("不好吃");
        productInfo.setProductIcon("http://www.xxx.com");
        productInfo.setProductStock(100);
        productInfo.setProductPrice(new BigDecimal(2.5));
        productInfoRepository.save(productInfo);

    }

    @Test
    public void findAll(){
        for (ProductInfo productInfo : productInfoRepository.findAll()) {
            System.out.println(productInfo);
        }
    }


    @Test
    public void findByProductStatusTest(){
        for (ProductInfo productInfo : productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode())) {
            System.out.println(productInfo);
        }
    }
    @Test
    public void findOne(String productInfoId){
    }

}