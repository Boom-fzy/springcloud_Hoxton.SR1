package com.fzy.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fzy.entities.CommonResult;
import com.fzy.entities.Payment;
import com.fzy.service.PaymentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PaymentController {

    @Resource
    private PaymentService paymentService;

	@PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("******���������Ϊ��" + payment);
        log.info("******��������" + result);

        if(result > 0){
            return new CommonResult(200, "�������ݿ�ɹ�", result);
        }else{
            return new CommonResult(444, "�������ݿ�ʧ��");
        }
    }


	@GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("******��ѯ�����" + payment);

        if(payment != null){
            return new CommonResult(200, "��ѯ�ɹ�", payment);
        }else{
            return new CommonResult(444, "û�ж�Ӧ��¼����ѯID��" + id);
        }
    }

}
