package com.iflytek.lcnproduct.service;

import com.iflytek.lcnproduct.entity.Product;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author llchen12
 * @since 2018-05-03
 */
public interface IProductService extends IService<Product> {


   Integer calculateMoney(Integer pid, Integer pnum);


}
