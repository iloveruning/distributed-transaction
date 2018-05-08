package com.iflytek.bytetcc.sample.consumer.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.iflytek.bytetcc.sample.consumer.entity.Account;
import com.iflytek.bytetcc.sample.consumer.mapper.AccountMapper;
import com.iflytek.bytetcc.sample.consumer.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author llchen12
 * @date 2018/5/8
 */
@Service("transferServiceConfirm")
public class AccountServiceConfirm extends ServiceImpl<AccountMapper,Account> implements AccountService {

    @Override
    @Transactional
    public void transfer(String sourceAcctId, String targetAcctId, double amount) {
        int value = this.baseMapper.confirmIncrease(targetAcctId, amount);
        if (value != 1) {
            throw new IllegalStateException("ERROR!");
        }
        System.out.printf("done increase: acct= %s, amount= %7.2f%n", targetAcctId, amount);
    }
}
