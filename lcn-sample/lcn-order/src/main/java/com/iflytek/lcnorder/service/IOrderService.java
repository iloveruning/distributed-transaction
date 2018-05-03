package com.iflytek.lcnorder.service;

import com.iflytek.lcnorder.entity.Order;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author llchen12
 * @since 2018-05-03
 */
public interface IOrderService extends IService<Order> {


    /**
     * 下订单
     * @param order 订单
     */
    void placeOrder(Order order);

}
