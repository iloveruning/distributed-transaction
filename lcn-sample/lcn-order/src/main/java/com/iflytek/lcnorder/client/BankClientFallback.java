package com.iflytek.lcnorder.client;

/**
 * @author llchen12
 * @date 2018/5/3
 */
public class BankClientFallback implements BankClient {

    @Override
    public String decreaseAccount(Integer uid, Integer money) {
        return "fallback";
    }
}
