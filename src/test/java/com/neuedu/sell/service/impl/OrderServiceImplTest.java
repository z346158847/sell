package com.neuedu.sell.service.impl;

import com.neuedu.sell.dto.OrderDTO;
import com.neuedu.sell.entity.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    @Test
    public void createTest(){

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerPhone("4008208820");
        orderDTO.setBuyerAddress("天津西八道");
        orderDTO.setBuyerOpenid("123456789");
        orderDTO.setBuyerName("王老五");
        List<OrderDetail> list = new ArrayList<>();

        list.add(new OrderDetail("2018110101",10));
        list.add(new OrderDetail("2018110102",5));
        orderDTO.setOrderDetailList(list);
        orderService.create(orderDTO);

    }

}