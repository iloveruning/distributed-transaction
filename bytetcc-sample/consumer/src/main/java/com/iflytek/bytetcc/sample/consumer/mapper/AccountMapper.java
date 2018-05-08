package com.iflytek.bytetcc.sample.consumer.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.iflytek.bytetcc.sample.consumer.entity.Account;
import org.apache.ibatis.annotations.Param;

/**
 * @author llchen12
 * @date 2018/5/8
 */
public interface AccountMapper extends BaseMapper<Account> {

     int increaseAmount(@Param("acctId") String accountId, @Param("amount") double amount);

     Double getAcctAmount(@Param("acctId") String accountId);

     int confirmIncrease(@Param("acctId") String accountId, @Param("amount") double amount);

     int cancelIncrease(@Param("acctId") String accountId, @Param("amount") double amount);
}
