package com.iflytek.lcnaccount.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.codingapi.tx.annotation.TxTransaction;
import com.google.common.collect.Maps;
import com.iflytek.lcnaccount.entity.Account;
import com.iflytek.lcnaccount.mapper.AccountMapper;
import com.iflytek.lcnaccount.service.IAccountService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author llchen12
 * @since 2018-05-05
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {

    @Override
    @TxTransaction
    @Transactional(rollbackFor = Exception.class)
    public void decreaseAccount(Integer uid, Integer money) {

        List<Account> list = this.baseMapper.selectList(new EntityWrapper<Account>().eq("user_id",uid));

        if (list.isEmpty()){
            throw new RuntimeException("用户不存在！");
        }
        Account account=list.get(0);
        Integer res=account.getAccount()-money;
        if (account.getAccount()<money){
            throw new RuntimeException("余额不足!");
        }

        account.setAccount(res);
        this.baseMapper.updateById(account);
    }
}
