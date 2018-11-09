package com.notification.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.notification.dto.RequestDTO;

@Service
public class RabbitMQSender {

	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	@Value("${notification.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${notification.rabbitmq.routingkey}")
	private String routingkey;	
	
	public void send(RequestDTO requestDTO) {
		rabbitTemplate.convertAndSend(exchange, routingkey, requestDTO);
		System.out.println("Send msg = " + requestDTO);	    
	}
	
	public void send(String S) {
		rabbitTemplate.convertAndSend(exchange, routingkey, S);
		System.out.println("Send msg = " + S);	    
	}
}
