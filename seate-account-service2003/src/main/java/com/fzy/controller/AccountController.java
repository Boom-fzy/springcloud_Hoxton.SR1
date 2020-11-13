package com.fzy.controller;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fzy.domain.CommonResult;
import com.fzy.service.AccountService;

@RestController
public class AccountController {

    @Resource
    AccountService accountService;

    @SuppressWarnings("rawtypes")
	@RequestMapping("/account/decrease")
    public CommonResult decrease(@RequestParam("userId") Long userId,@RequestParam("money") BigDecimal money) {
        accountService.decrease(userId,money);
        return new CommonResult(200,"账户余额扣减成功！");
    }
}
