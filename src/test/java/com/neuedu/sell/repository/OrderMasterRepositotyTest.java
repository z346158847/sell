package com.neuedu.sell.repository;

import com.neuedu.sell.entity.OrderMaster;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositotyTest {

    private static final String OPENID = "abc123";

    @Autowired
    private OrderMasterRepositoty orderMasterRepositoty;


    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123456788");
        orderMaster.setBuyerName("萨达姆");
        orderMaster.setBuyerAddress("地区");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setBuyerPhone("1233333333");
        orderMaster.setOrderAmount(new BigDecimal(20.5));
        orderMasterRepositoty.save(orderMaster);
    }
    @Test
    public void findByBuyerOpenidTest(){
        Page<OrderMaster> page = orderMasterRepositoty.findByBuyerOpenid(OPENID,new PageRequest(0,1));
        for (OrderMaster orderMaster : page.getContent()) {
            System.out.println(orderMaster);
        }
    }


}