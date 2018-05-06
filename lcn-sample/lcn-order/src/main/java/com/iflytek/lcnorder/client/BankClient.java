package com.iflytek.lcnorder.client;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author llchen12
 * @date 2018/5/3
 */
@FeignClient(value = "lcn-account"/*,fallback = BankClientFallback.class*/)
public interface BankClient {


    @GetMapping("/account/dec")
    String decreaseAccount(@RequestParam(value = "uid") Integer uid,@RequestParam(value = "money") Integer money);

}
