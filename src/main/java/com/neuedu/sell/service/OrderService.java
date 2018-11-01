package com.neuedu.sell.service;

import com.neuedu.sell.dto.OrderDTO;
import com.neuedu.sell.repository.OrderDetailRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {


    /**
     * 创建订单
     */
    OrderDTO create(OrderDTO orderDTO);


    /**
     * 根据订单ID来查询
     * @param openId
     * @return
     */
    OrderDTO findOne(String orderId);

    /**
     * 查询订单列表
     * @param buyerOpenid
     * @param pageable
     * @return
     */
    Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);

    /**
     * 取消订单
     * @param orderDTO
     * @return
     */
    OrderDTO cancel(OrderDTO orderDTO);

    /**
     *完成订单
     * @param orderDTO
     * @return
     */
    OrderDTO finish(OrderDTO orderDTO);

    /**
     *支付
     * @param orderDTO
     * @return
     */
    OrderDTO paid(OrderDTO orderDTO);


}
