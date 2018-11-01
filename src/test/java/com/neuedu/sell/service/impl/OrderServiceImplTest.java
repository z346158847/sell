package com.neuedu.sell.service.impl;

import com.neuedu.sell.dto.OrderDTO;
import com.neuedu.sell.entity.OrderDetail;
import com.neuedu.sell.enums.OrderStatusEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
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
    @Test
    public void findOne(){
        OrderDTO orderDTO = orderService.findOne("1541033641752757492");
        System.out.println(orderDTO);
    }
    @Test
    public void findList(){
        Page<OrderDTO> page = orderService.findList("123456789",new PageRequest(0,2));
        for (OrderDTO orderDTO : page.getContent()) {
            System.out.println(orderDTO);
        }
    }

    @Test
    public void cancelTest(){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("1541033641752757492");
        List<OrderDetail> list = new ArrayList<>();

        list.add(new OrderDetail("2018110101",10));
        list.add(new OrderDetail("2018110102",5));
        orderDTO.setOrderDetailList(list);
        orderService.cancel(orderDTO);


    }

}