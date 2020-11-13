package com.fzy.service;

import java.math.BigDecimal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fzy.domain.CommonResult;

@FeignClient(value = "seata-account-service")
public interface AccountService {

    @SuppressWarnings("rawtypes")
	@PostMapping(value = "/account/decrease")
    CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);
}
