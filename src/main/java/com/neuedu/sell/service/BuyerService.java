package com.neuedu.sell.service;

import com.neuedu.sell.dto.OrderDTO;

public interface BuyerService {

    OrderDTO findOrderOne(String openid,String orderId);

    OrderDTO cancelOrder(String openid, String orderId);
}
