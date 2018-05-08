package com.iflytek.bytetcc.sample.consumer.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author llchen12
 * @date 2018/5/8
 */
@FeignClient(value = "bytetcc-provider")
public interface AccountClient {
    @RequestMapping(method = RequestMethod.POST, value = "/increase")
    void increaseAmount(@RequestParam("acctId") String accountId, @RequestParam("amount") double amount);

    @RequestMapping(method = RequestMethod.POST, value = "/decrease")
    void decreaseAmount(@RequestParam("acctId") String accountId, @RequestParam("amount") double amount);
}
