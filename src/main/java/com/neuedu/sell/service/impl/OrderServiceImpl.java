package com.neuedu.sell.service.impl;

import com.neuedu.sell.converter.OrderForm2OrderDTOConverter;
import com.neuedu.sell.converter.OrderMaster2OrderDTOConverter;
import com.neuedu.sell.dto.CartDTO;
import com.neuedu.sell.dto.OrderDTO;
import com.neuedu.sell.entity.OrderDetail;
import com.neuedu.sell.entity.OrderMaster;
import com.neuedu.sell.entity.ProductInfo;
import com.neuedu.sell.enums.OrderStatusEnum;
import com.neuedu.sell.enums.PayStatusEnum;
import com.neuedu.sell.enums.ResultEnum;
import com.neuedu.sell.exception.SellException;
import com.neuedu.sell.repository.OrderDetailRepository;
import com.neuedu.sell.repository.OrderMasterRepositoty;
import com.neuedu.sell.service.OrderService;
import com.neuedu.sell.service.ProductInfoService;
import com.neuedu.sell.utils.KeyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderMasterRepositoty orderMasterRepositoty;
    @Autowired
    private ProductInfoService productInfoService;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

        String orderId = KeyUtils.generateUniqueKey();

        //1.查询商品
        //拿出所有商品
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
            if (productInfo == null){
                //当商品不存在时抛出异常
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2.计算总价
            orderAmount = orderAmount.add(productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())))  ;
            //复制商品信息
            BeanUtils.copyProperties(productInfo,orderDetail);
            //设置订单Id
            orderDetail.setOrderId(orderId);
            //设置此数据的Id
            orderDetail.setDetailId(KeyUtils.generateUniqueKey());
            //设置总价
            orderDTO.setOrderAmount(orderAmount);
            //保存到数据库中//订单详情入库
            orderDetailRepository.save(orderDetail);
        }
        //3.订单主表入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO,orderMaster);

        orderMasterRepositoty.save(orderMaster);

        List<CartDTO> cartDTOList = new ArrayList<>();
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            cartDTOList.add(new CartDTO(orderDetail.getProductId(),orderDetail.getProductQuantity()));
        }
        //4.扣库存
        productInfoService.decreaseStock(cartDTOList);

        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {

        OrderMaster orderMaster = orderMasterRepositoty.findOne(orderId);
        if (orderMaster == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (orderDetailList.size() == 0){
            throw new SellException(ResultEnum.ORDERDETAILE_NOT_EXIST);
        }
        OrderDTO orderDTO = OrderMaster2OrderDTOConverter.convert(orderMaster);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {

        Page<OrderMaster> page = orderMasterRepositoty.findByBuyerOpenid(buyerOpenid,pageable);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(page.getContent());

        return new PageImpl<>(orderDTOList,pageable,page.getTotalElements());
    }


    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        //订单应该从数据库中查出来
        OrderMaster orderMaster = orderMasterRepositoty.findOne(orderDTO.getOrderId());
        //1.判断订单状态
        if (!orderMaster.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //2.修改订单状态
        orderMaster.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        orderMasterRepositoty.save(orderMaster);
        //3.返还库存
        List<CartDTO> cartDTOList = new ArrayList<>();
        //根据OrderId查询orderDetail集合
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderMaster.getOrderId());
        for (OrderDetail orderDetail : orderDetailList) {
            cartDTOList.add(new CartDTO(orderDetail.getProductId(),orderDetail.getProductQuantity()));
        }
        productInfoService.increaseStock(cartDTOList);
        //4.如果已支付需要退款
        orderDTO.setBuyerOpenid(orderMaster.getBuyerOpenid());
        return orderDTO;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        //1.从数据库中查询信息
        OrderMaster orderMaster = orderMasterRepositoty.findOne(orderDTO.getOrderId());
        //2.判断状态
        if (!orderMaster.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //3.修改状态
        orderMaster.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        //4.保存到数据库
        orderMasterRepositoty.save(orderMaster);
        return orderDTO;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        //1.从数据库查询信息
        OrderMaster orderMaster = orderMasterRepositoty.findOne(orderDTO.getOrderId());
        //2.判断状态
        if (orderMaster.getPayStatus().equals(PayStatusEnum.PAID.getCode())){
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //3.修改状态
        orderMaster.setPayStatus(PayStatusEnum.PAID.getCode());
        //4.保存到数据库
        orderMasterRepositoty.save(orderMaster);
        return orderDTO;
    }


    @Override
    public Page<OrderDTO> findList(Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepositoty.findAll(pageable);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());


        return new PageImpl<>(orderDTOList,pageable,orderMasterPage.getTotalElements());
    }


}
