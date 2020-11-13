package com.fzy.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.fzy.domain.Order;

/**
 * @Author EiletXie
 * @Since 2020/3/18 21:16
 */
@Mapper
public interface OrderDao {
  
    int create(Order order);

   
    int update(@Param("userId") Long userId, @Param("status") Integer status);
}
