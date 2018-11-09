package com.sendnotification.serviceimpl;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notification.dto.RequestDTO;
import com.sendnotification.mailService.MailNotification;
import com.sendnotification.service.NotificationService;
import com.sendnotification.slackService.SlackService;

@Service
public class NotificationServiceImpl implements NotificationService{
	@Autowired
	private MailNotification mailNotification;
	
	@Autowired
	private SlackService slackService;
	
	@Override
	public void sendNotification(RequestDTO requestDTO) throws MessagingException, MalformedURLException, IOException {

		mailNotification.sendMail(requestDTO);
		slackService.sendMessageToSlack(requestDTO);
	}

}
