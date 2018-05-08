package com.iflytek.bytetcc.sample.consumer.service;

import com.baomidou.mybatisplus.service.IService;
import com.iflytek.bytetcc.sample.consumer.entity.Account;



/**
 * @author llchen12
 * @date 2018/5/8
 */

public interface AccountService extends IService<Account> {

    void transfer(String sourceAcctId, String targetAcctId, double amount);
}
