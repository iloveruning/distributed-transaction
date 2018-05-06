package com.iflytek.lcnaccount.controller;


import com.iflytek.lcnaccount.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author llchen12
 * @since 2018-05-05
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @GetMapping("dec")
    public String decreaseMoney(@RequestParam("uid") Integer uid,@RequestParam("money") Integer money){
        accountService.decreaseAccount(uid, money);
        return "付款成功";
    }
}

