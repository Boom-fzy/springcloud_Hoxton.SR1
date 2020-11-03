package com.fzy.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CircuitPaymentController {
	
	private final static Map<String,String> hashSQL = new HashMap<>();

    @GetMapping(value = "/paymentSQL/nacos/{id}")
    public String getPayment(@PathVariable("id") Integer id) {
        return hashSQL.get(id.toString());
    }
    
    static {
    	hashSQL.put("1", "9004-qwertyuiop136666");
    	hashSQL.put("2", "9004-1qaz12wsx3edfdff");
    	hashSQL.put("3", "9004-erse3467okjscsde");
    }
}