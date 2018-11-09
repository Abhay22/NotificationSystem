package com.sendnotification.listener;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.mail.MessagingException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.notification.dto.RequestDTO;
import com.sendnotification.mailService.MailNotification;
import com.sendnotification.service.NotificationService;

@Component
@PropertySource("classpath:application.properties")
public class RabbitMQListener {
	
	@Autowired
	private NotificationService notificationService;
	
	@RabbitListener(queues="${notification.rabbitmq.queue}", containerFactory="jsonFactory")
	
	public void receiveMessage(RequestDTO requestDTO) throws MessagingException, MalformedURLException, IOException{
		
		notificationService.sendNotification(requestDTO);
		}

}
