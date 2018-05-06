package com.iflytek.lcnorder.client;

import org.springframework.stereotype.Component;

/**
 * @author llchen12
 * @date 2018/5/3
 */
@Component
public class BankClientFallback implements BankClient {

    @Override
    public String decreaseAccount(Integer uid, Integer money) {
        return "fallback";
    }
}
