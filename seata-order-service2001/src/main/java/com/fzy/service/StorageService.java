package com.fzy.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fzy.domain.CommonResult;


@FeignClient(value = "seata-storage-service")
public interface StorageService {

    @SuppressWarnings("rawtypes")
	@PostMapping(value = "/storage/decrease")
    CommonResult decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}
