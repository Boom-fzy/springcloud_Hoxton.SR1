package com.fzy.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.fzy.dao"})
public class MyBatisConfig {


}
