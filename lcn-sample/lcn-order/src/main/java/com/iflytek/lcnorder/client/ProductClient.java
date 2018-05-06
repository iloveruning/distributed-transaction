package com.iflytek.lcnorder.client;

import org.springframework.cloud.netflix.feign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author llchen12
 * @date 2018/5/3
 */
@FeignClient(value = "lcn-product"/*,fallback = ProductClientFallback.class*/)
public interface ProductClient {


    @GetMapping(value = "/product/calculate")
   Integer forOrder(@RequestParam(value = "pid") Integer pid,@RequestParam(value = "pnum") Integer pnum);
}
