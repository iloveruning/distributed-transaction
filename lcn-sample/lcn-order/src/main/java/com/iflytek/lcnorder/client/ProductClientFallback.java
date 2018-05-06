package com.iflytek.lcnorder.client;

import org.springframework.stereotype.Component;

/**
 * @author llchen12
 * @date 2018/5/3
 */
@Component
public class ProductClientFallback implements ProductClient {


    @Override
    public Integer forOrder(Integer pid, Integer pnum) {
        return -1;
    }
}
