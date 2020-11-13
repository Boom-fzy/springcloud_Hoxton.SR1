package com.fzy.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fzy.domain.CommonResult;
import com.fzy.service.StorageService;


@RestController
public class StorageController {

    @Resource
    private StorageService storageService;

    @SuppressWarnings("rawtypes")
	@RequestMapping("/storage/decrease")
    public CommonResult decrease(Long productId,Integer count) {
        storageService.decrease(productId, count);
        return new CommonResult(200,"¿â´æ¿Û¼õ³É¹¦£¡");
    }
}
