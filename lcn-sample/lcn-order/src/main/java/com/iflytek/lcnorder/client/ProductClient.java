package com.iflytek.lcnorder.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author llchen12
 * @date 2018/5/3
 */
@FeignClient(value = "lcn-product",fallback = ProductClientFallback.class)
public interface ProductClient {


    @GetMapping("/product/order")
   Integer forOrder(@RequestParam("pid") Integer pid,@RequestParam("pnum") Integer pnum);
}
