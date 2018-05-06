package com.iflytek.lcnproduct.controller;


import com.iflytek.lcnproduct.service.IProductService;
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
 * @since 2018-05-03
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("/calculate")
    public Integer calculate(@RequestParam("pid") Integer pid,@RequestParam("pnum") Integer pnum){
        return productService.calculateMoney(pid,pnum);
    }

}

