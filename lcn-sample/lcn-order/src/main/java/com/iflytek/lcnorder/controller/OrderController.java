package com.iflytek.lcnorder.controller;


import com.iflytek.lcnorder.entity.Order;
import com.iflytek.lcnorder.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author llchen12
 * @since 2018-05-03
 */
@RestController
@RequestMapping("/order")
public class OrderController {


    @Autowired
    private IOrderService orderService;

    @PostMapping("/place")
    public String placeOrder(@RequestBody Order order){

        orderService.placeOrder(order);
        return "SUCCESSFUL!";
    }


}

