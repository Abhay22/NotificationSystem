package com.notification.dto;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_DEFAULT)
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,property="@id", scope = RequestDTO.class)

public class RequestDTO  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String from;
	private String fromPassword;// Password needs to be encrypted...
	private String to;
	private String subject;
	private String payload;
	private String fromPhone;
	private String toPhone;
	private String messageType;
	private String slackVerificationToken;
	private String routeeAppId;
	private String routeeAppSecret;
	
	
	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}
	/**
	 * @param from the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}
	/**
	 * @return the to
	 */
	public String getTo() {
		return to;
	}
	/**
	 * @param to the to to set
	 */
	public void setTo(String to) {
		this.to = to;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the payload
	 */
	public String getPayload() {
		return payload;
	}
	/**
	 * @param payload the payload to set
	 */
	public void setPayload(String payload) {
		this.payload = payload;
	}
	
	/*@Override
	public String toString() {
		return "RequestDTO [From= " + from + ", to= " + to + ", Subject= "+subject+"]";
	}*/
	
	@Override
	public String toString(){
		JSONObject jsonObject = new JSONObject();
		try{
			jsonObject.put("From", this.from);
			jsonObject.put("To", this.to);
			jsonObject.put("Subject", this.subject);
			jsonObject.put("Payload", this.payload);
		}
		catch(JSONException e){
			//return jsonObject.toString();
		}
		
		return jsonObject.toString();
	}
	/**
	 * @return the fromPassword
	 */
	public String getFromPassword() {
		return fromPassword;
	}
	/**
	 * @param fromPassword the fromPassword to set
	 */
	public void setFromPassword(String fromPassword) {
		this.fromPassword = fromPassword;
	}
	/**
	 * @return the fromPhone
	 */
	public String getFromPhone() {
		return fromPhone;
	}
	/**
	 * @param fromPhone the fromPhone to set
	 */
	public void setFromPhone(String fromPhone) {
		this.fromPhone = fromPhone;
	}
	/**
	 * @return the toPhone
	 */
	public String getToPhone() {
		return toPhone;
	}
	/**
	 * @param toPhone the toPhone to set
	 */
	public void setToPhone(String toPhone) {
		this.toPhone = toPhone;
	}
	/**
	 * @return the messageType
	 */
	public String getMessageType() {
		return messageType;
	}
	/**
	 * @param messageType the messageType to set
	 */
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	/**
	 * @return the slackVerificationToken
	 */
	public String getSlackVerificationToken() {
		return slackVerificationToken;
	}
	/**
	 * @param slackVerificationToken the slackVerificationToken to set
	 */
	public void setSlackVerificationToken(String slackVerificationToken) {
		this.slackVerificationToken = slackVerificationToken;
	}
	/**
	 * @return the routeeAppId
	 */
	public String getRouteeAppId() {
		return routeeAppId;
	}
	/**
	 * @param routeeAppId the routeeAppId to set
	 */
	public void setRouteeAppId(String routeeAppId) {
		this.routeeAppId = routeeAppId;
	}
	/**
	 * @return the routeeAppSecret
	 */
	public String getRouteeAppSecret() {
		return routeeAppSecret;
	}
	/**
	 * @param routeeAppSecret the routeeAppSecret to set
	 */
	public void setRouteeAppSecret(String routeeAppSecret) {
		this.routeeAppSecret = routeeAppSecret;
	}
	

}
