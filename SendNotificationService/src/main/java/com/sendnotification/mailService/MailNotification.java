package com.sendnotification.mailService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import com.notification.dto.RequestDTO;

@Service
public class MailNotification {
	
	private static final Logger logger = Logger.getLogger(MailNotification.class);
	
	@Value("${smpt.host}")
	private String emailHost;
	
	@Value("${smpt.port}")
	private String emailPort;
	
	@Value("${smpt.username}")
	private String username;
	
	@Value("${smpt.password}")
	private String password;
	
	Properties emailProperties=new Properties();
	Session mailSession;
	MimeMessage emailMessage;
	


	
	public void sendMail(RequestDTO requestDTO) throws MessagingException{
		
		//System.out.println("EmailPort: "+emailPort +" EmailUser: "+username+" Pass: "+password);
		emailProperties.put("mail.smtp.host", emailHost);
		emailProperties.put("mail.smtp.port", emailPort);		
		emailProperties.put("mail.smtp.auth", "true");
		emailProperties.put("mail.smtp.starttls.enable", "true");
		
		Session session = Session.getInstance(emailProperties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(requestDTO.getFrom(), requestDTO.getFromPassword());
            }});
		try{
		MimeMessage msg = new MimeMessage(session);
		String to = requestDTO.getTo();
		InternetAddress[] address = InternetAddress.parse(to, true);
		msg.setRecipients(Message.RecipientType.TO, address);
		msg.setSubject(requestDTO.getSubject());
        msg.setSentDate(new Date());
        msg.setText(requestDTO.getPayload());
        msg.setHeader("Payload Mail", "1");
        Transport.send(msg);
        logger.info("Mail has been sent successfully");
		}
		catch(MessagingException mesExp){
			logger.info("Mail Not Sent!! "+mesExp);
		}
		
	}
	
	/*public void createMail(Session session){
		MimeMessage msg = new MimeMessage(session);
		 
		
	}*/
	
	private Properties setMailServerProperties(Properties emailProperties){
		emailProperties.put("mail.smtp.port", emailPort);
		emailProperties.put("mail.smtp.host", emailHost);
		emailProperties.put("mail.smtp.auth", "true");
		emailProperties.put("mail.smtp.starttls.enable", "true");
		
		return emailProperties;
		
	}
	
}
