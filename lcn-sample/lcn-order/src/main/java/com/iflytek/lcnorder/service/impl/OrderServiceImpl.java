package com.iflytek.lcnorder.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.codingapi.tx.annotation.TxTransaction;
import com.iflytek.lcnorder.client.BankClient;
import com.iflytek.lcnorder.client.ProductClient;
import com.iflytek.lcnorder.entity.Order;
import com.iflytek.lcnorder.mapper.OrderMapper;
import com.iflytek.lcnorder.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author llchen12
 * @since 2018-05-03
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private BankClient bankClient;

    @Autowired
    private ProductClient productClient;


    @Override
    @TxTransaction(isStart = true)
    @Transactional(rollbackFor = Exception.class)
    public void placeOrder(Order order) {


        Integer res1 = this.baseMapper.insert(order);

        Integer money= productClient.forOrder(order.getProductId(), order.getProductNum());


       String map2 = bankClient.decreaseAccount(order.getUserId(), money);

        System.out.println(map2);

    }
}
