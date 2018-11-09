package com.sendnotification.service;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.mail.MessagingException;

import org.springframework.stereotype.Component;

import com.notification.dto.RequestDTO;

@Component
public interface NotificationService {

	void sendNotification(RequestDTO requestDTO) throws MessagingException, MalformedURLException, IOException;
}
