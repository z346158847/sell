package com.neuedu.sell.repository;

import com.neuedu.sell.entity.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("1234561");
        orderDetail.setOrderId("123456788");
        orderDetail.setProductIcon("wwwww");
        orderDetail.setProductId("1111");
        orderDetail.setProductPrice(new BigDecimal(20));
        orderDetail.setProductQuantity(5);
        orderDetail.setProductName("冰粥");
        orderDetailRepository.save(orderDetail);

    }
    @Test
    public void findByOrderId(){

        for (OrderDetail orderDetail : orderDetailRepository.findByOrderId("123456788")) {
            System.out.println(orderDetail);
        }
    }

}