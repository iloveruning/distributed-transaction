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

        Product product = this.baseMapper.selectById(pid);
        if (product==null){
            throw new RuntimeException("该商品不存在！");
        }
        Integer res=product.getStock()-pnum;
        if (res<0){
            throw new RuntimeException("库存不足！");
        }
        product.setStock(res);

        Integer res1 = this.baseMapper.updateById(product);
        if (res1!=1){
            throw new RuntimeException("更新库存失败");
        }
        return product.getPrice()*pnum;
    }
}
