package com.fzy.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.fzy.entities.CommonResult;
import com.fzy.entities.Payment;

@SuppressWarnings({"rawtypes","unchecked"})
public class CustomerBlockHandler {

	public static CommonResult handlerException(BlockException exception) {
        return  new CommonResult(444,"按照客户自定义的Glogal 全局异常处理 ---- 1",new Payment(2020L,"serial003"));
    }

    public static CommonResult handlerException2(BlockException exception) {
        return  new CommonResult(444,"按照客户自定义的Glogal 全局异常处理 ---- 2",new Payment(2020L,"serial003"));
    }
}
