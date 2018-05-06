package com.iflytek.lcnaccount.service;

import com.iflytek.lcnaccount.entity.Account;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author llchen12
 * @since 2018-05-05
 */
public interface IAccountService extends IService<Account> {


    void decreaseAccount(Integer uid,Integer money);

}
