package com.iflytek.lcnorder.client;

/**
 * @author llchen12
 * @date 2018/5/3
 */
public class ProductClientFallback implements ProductClient {


    @Override
    public Integer forOrder(Integer pid, Integer pnum) {
        return -1;
    }
}
