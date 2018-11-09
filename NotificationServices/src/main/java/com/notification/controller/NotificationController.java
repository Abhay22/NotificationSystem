package com.notification.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.notification.dto.RequestDTO;
import com.notification.service.RabbitMQSender;
import com.notification.status.StatusDTO;

@RestController
public class NotificationController {
	
	private static final Logger logger = Logger.getLogger(NotificationController.class);
	private static final String EMAIL = "emailRequest";
	private static final String SLACK = "slackRequest";
	
	
	
	@Autowired
	RabbitMQSender rabbitMQSender;
	
	@RequestMapping(value="/api/dataPayload",method=RequestMethod.POST)
	public StatusDTO dataPayload(@RequestBody RequestDTO requestDTO){
		StatusDTO statusDTO=new StatusDTO();
		try{
			if(requestDTO.getMessageType().equals(EMAIL)){
			if(requestDTO.getTo()!=null && requestDTO.getTo()!=null
					&& !requestDTO.getTo().equals("") && !requestDTO.getFrom().equals("")){
			statusDTO=validateEmail(requestDTO.getTo(), statusDTO);
			statusDTO=validateEmail(requestDTO.getFrom(), statusDTO);
			}
			else{
				throw new Exception(new NullPointerException());
			}
			}
			else if(requestDTO.getMessageType().equals(SLACK)){
				//Slack details verification
			}
		}
		catch(Exception e){
			logger.info("Invalid To or From Mail ID");
			statusDTO.setStatusMessage("Invalid To or From Mail ID");
			return statusDTO;
		}
		
		rabbitMQSender.send(requestDTO);
		statusDTO.setStatusMessage("You will receive mail soon");
		return statusDTO;	
	}

	private static StatusDTO validateEmail(String eMailID, StatusDTO statusDTO) throws Exception {
		final Pattern VALID_EMAIL_ADDRESS_REGEX = 
			    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		
	        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(eMailID);
	        if(!matcher.find()){
	        	throw new Exception("Invalid Email");
	        }
	        else{
	        	statusDTO.setStatusMessage("Valid Email!!");
	        }
			return statusDTO;
		
	}	
}
