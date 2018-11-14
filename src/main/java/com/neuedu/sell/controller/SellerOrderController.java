package com.neuedu.sell.controller;

import com.neuedu.sell.dto.OrderDTO;
import com.neuedu.sell.entity.OrderDetail;
import com.neuedu.sell.enums.ResultEnum;
import com.neuedu.sell.exception.SellException;
import com.neuedu.sell.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.soap.Detail;
import java.util.List;

@Controller
@RequestMapping("/seller/order")
public class SellerOrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 创建订单
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "10")Integer size){
        ModelAndView model = new ModelAndView("order/list");

        PageRequest pageRequest = new PageRequest(page - 1,size);

        Page<OrderDTO> orderDTOPage = orderService.findList(pageRequest);

        model.addObject("orderDTOPage",orderDTOPage);

        model.addObject("currentPage", page);
        model.addObject("size", size);
        return model;

    }


    /**
     * 取消订单
     * @param orderId
     * @return
     */
    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId")String orderId){
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);
        }catch (SellException e){
            ModelAndView errorModel = new ModelAndView();
            errorModel.setViewName("common/error");
            errorModel.addObject("msg",e.getMessage());
            errorModel.addObject("url","/sell/seller/order/list");
            return errorModel;
        }
        ModelAndView successModel = new ModelAndView();
        successModel.setViewName("common/success");
        successModel.addObject("msg",ResultEnum.ORDER_CANCEL_SUCCESS.getMessage());
        successModel.addObject("url","/sell/seller/order/list");

        return successModel;
    }

    /**
     * 订单详情
     * @return
     */
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId")String orderId){
            OrderDTO orderDTO = new OrderDTO();
        try {
            orderDTO = orderService.findOne(orderId);
        }catch (SellException e){
            ModelAndView errorModel = new ModelAndView();
            errorModel.setViewName("common/error");
            errorModel.addObject("msg",e.getMessage());
            errorModel.addObject("url","/sell/seller/order/list");
            return errorModel;
        }
        ModelAndView successModel = new ModelAndView();
        successModel.setViewName("order/detail");
        successModel.addObject("orderDTO",orderDTO);
        return successModel;
    }



    @GetMapping("/finish")
    public ModelAndView finish(@RequestParam("orderId")String orderId){
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.finish(orderDTO);
        }catch (SellException e){
            ModelAndView errorModel = new ModelAndView();
            errorModel.setViewName("common/error");
            errorModel.addObject("msg",e.getMessage());
            errorModel.addObject("url","/sell/seller/order/list");
            return errorModel;
        }
        ModelAndView successModel = new ModelAndView();
        successModel.setViewName("common/success");
        successModel.addObject("msg",ResultEnum.ORDER_FINISH_SUCCESS.getMessage());
        successModel.addObject("url","/sell/seller/order/list");

        return successModel;
    }

}
