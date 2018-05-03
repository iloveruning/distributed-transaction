package com.iflytek.lcnorder.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author llchen12
 * @date 2018/5/3
 */
@FeignClient(value = "lcn-bank",fallback = BankClientFallback.class)
public interface BankClient {


    @GetMapping("/bank/dec")
    String decreaseAccount(@RequestParam("uid") Integer uid,@RequestParam("money") Integer money);

}
