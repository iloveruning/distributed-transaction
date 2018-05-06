package com.iflytek.lcnorder.service;


import com.baomidou.mybatisplus.service.IService;
import com.iflytek.lcnorder.entity.Orders;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author llchen12
 * @since 2018-05-03
 */
public interface IOrderService extends IService<Orders> {


    /**
     * 下订单
     * @param order 订单
     */
    void placeOrder(Orders order);

}
