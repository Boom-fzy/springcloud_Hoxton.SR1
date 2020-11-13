package com.fzy.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fzy.dao.OrderDao;
import com.fzy.domain.Order;
import com.fzy.service.AccountService;
import com.fzy.service.OrderService;
import com.fzy.service.StorageService;

import io.seata.spring.annotation.GlobalTransactional;


@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;
    @Resource
    private AccountService accountService;
    @Resource
    private StorageService storageService;

    @Override
    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class)
    public void create(Order order) {
        orderDao.create(order);

        storageService.decrease(order.getProductId(), order.getCount());

        accountService.decrease(order.getUserId(), order.getMoney());

        orderDao.update(order.getUserId(), 0);

    }
}
