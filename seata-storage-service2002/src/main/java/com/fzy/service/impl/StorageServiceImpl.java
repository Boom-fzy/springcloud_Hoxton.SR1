package com.fzy.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fzy.dao.StorageDao;
import com.fzy.service.StorageService;


@Service
public class StorageServiceImpl implements StorageService {


    @Resource
    private StorageDao storageDao;

    @Override
    public void decrease(Long productId, Integer count) {
        storageDao.decrease(productId,count);
    }
}
