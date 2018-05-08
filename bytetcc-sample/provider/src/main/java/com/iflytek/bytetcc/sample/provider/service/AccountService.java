package com.iflytek.bytetcc.sample.provider.service;




/**
 * @author llchen12
 * @date 2018/5/8
 */

public interface AccountService  {

     void increaseAmount(String accountId, double amount);

     void decreaseAmount(String accountId, double amount);
}
