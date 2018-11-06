package com.neuedu.sell.controller;


import com.neuedu.sell.VO.ResultVO;
import com.neuedu.sell.converter.OrderForm2OrderDTOConverter;
import com.neuedu.sell.dto.OrderDTO;
import com.neuedu.sell.entity.OrderDetail;
import com.neuedu.sell.enums.ResultEnum;
import com.neuedu.sell.exception.SellException;
import com.neuedu.sell.form.OrderForm;
import com.neuedu.sell.service.BuyerService;
import com.neuedu.sell.service.OrderService;
import com.neuedu.sell.utils.ResultVOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private BuyerService buyerService;

    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm, BindingResult bindingResult){
        //Valid配合OrderForm中的NotEmpty可以实现参数校验，再
        // 利用BindingResult中的hasErrors可以判断是否异常，
        //bindingResult.getFieldError().getDefaultMessage
        //返回NotEmpty注解上的message
        //1.校验参数的合法性
        if (bindingResult.hasErrors()){
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        //2.转化为OrderDTO的类型
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        //3.调用业务层去创建订单
        OrderDTO resultDTO = orderService.create(orderDTO);

        Map<String,String> map = new HashMap<>();
        map.put("orderId",resultDTO.getOrderId());
        return ResultVOUtils.success(map);
    }

    @GetMapping("/list")
    public ResultVO<List<OrderDetail>> list(@RequestParam("openid") String openid,
                                            @RequestParam(value = "page",defaultValue = "0") Integer page,
                                            @RequestParam(value = "size",defaultValue = "10") Integer size){
        //校验参数合法性
        if (StringUtils.isEmpty(openid)){
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
       //调用业务层处理
        PageRequest request = new PageRequest(page,size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid,request);
        //包装结果集
        return ResultVOUtils.success(orderDTOPage.getContent());

    }

    @GetMapping("/detail")
    public ResultVO detail(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId){
        //校验参数合法性
        if (StringUtils.isEmpty(openid)  || StringUtils.isEmpty(orderId)){
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        //查询
        //TODO 不安全会有横向越权问题
        //使用buyerService去判断
        OrderDTO orderDTO = buyerService.findOrderOne(openid,orderId);
        return ResultVOUtils.success(orderDTO);
    }

    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId){

        //校验参数合法性
        if (StringUtils.isEmpty(openid)  || StringUtils.isEmpty(orderId)){
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        //查询
        //TODO 不安全会有横向越权问题
        //使用buyerService去判断
        buyerService.cancelOrder(openid, orderId);

        return ResultVOUtils.success();
    }





}
