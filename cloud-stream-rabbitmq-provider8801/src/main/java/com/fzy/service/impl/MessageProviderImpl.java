package com.fzy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import com.fzy.service.IMessageProvider;

import cn.hutool.core.lang.UUID;

@EnableBinding(Source.class) // 定义消息的推送管道
public class MessageProviderImpl implements IMessageProvider {

	@Autowired
	private MessageChannel output;

	@Override
	public String send() {
		
		String serial = UUID.randomUUID().toString();

		output.send(MessageBuilder.withPayload(serial).build());
		
		System.out.println("******serial：" + serial);
		return serial;
	}

}
