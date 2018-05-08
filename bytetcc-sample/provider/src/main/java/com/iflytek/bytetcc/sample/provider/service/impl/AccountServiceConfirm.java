package com.iflytek.bytetcc.sample.provider.service.impl;


import com.iflytek.bytetcc.sample.provider.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author llchen12
 * @date 2018/5/8
 */
@Service("accountServiceConfirm")
public class AccountServiceConfirm  implements AccountService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public void increaseAmount(String acctId, double amount) {
        int value = this.jdbcTemplate.update(
                "update tb_account_one set amount = amount + ?, frozen = frozen - ? where acct_id = ?", amount, amount, acctId);
        if (value != 1) {
            throw new IllegalStateException("ERROR!");
        }
        System.out.printf("done increase: acct= %s, amount= %7.2f%n", acctId, amount);
    }

    @Override
    @Transactional
    public void decreaseAmount(String acctId, double amount) {
        int value = this.jdbcTemplate.update("update tb_account_one set frozen = frozen - ? where acct_id = ?", amount, acctId);
        if (value != 1) {
            throw new IllegalStateException("ERROR!");
        }
        System.out.printf("done decrease: acct= %s, amount= %7.2f%n", acctId, amount);
    }
}
