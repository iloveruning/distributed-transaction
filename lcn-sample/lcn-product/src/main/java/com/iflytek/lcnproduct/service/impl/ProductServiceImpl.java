package com.iflytek.lcnproduct.service.impl;

import com.codingapi.tx.annotation.TxTransaction;
import com.iflytek.lcnproduct.entity.Product;
import com.iflytek.lcnproduct.mapper.ProductMapper;
import com.iflytek.lcnproduct.service.IProductService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author llchen12
 * @since 2018-05-03
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Override
    @Transactional
    @TxTransaction
    public Integer calculateMoney(Integer pid, Integer pnum) {

        return null;
    }
}
