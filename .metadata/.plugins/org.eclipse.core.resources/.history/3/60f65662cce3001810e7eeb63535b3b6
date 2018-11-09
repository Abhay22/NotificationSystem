package com.sendnotification.slackService;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.notification.dto.RequestDTO;
import net.minidev.json.parser.JSONParser;

@Service
public class SlackService {

	
	
	private String slackVerificationToken = "slack-verification-token";
	private String slackCommandInputRegex = "^(\\+[0-9]{12})\\s(.*)$";
	private String routeeAppId = "your-routee-application-id";
	private String routeeAppSecret = "your-routee-application-secret";
	private String requestToken="your-request-token";
	
	public void sendMessageToSlack(RequestDTO requestDTO) throws MalformedURLException, IOException{
		String slackResponseMessage = "";
		String accessToken=routeeAuthenticate(routeeAppId, routeeAppSecret);
		boolean success = sendSmsMessage(accessToken, requestDTO.getPayload(), requestDTO.getFromPhone(), requestDTO.getToPhone());
		if (success) {
            slackResponseMessage = "Message successfully send to " + requestDTO.getTo();
            
        } else {
            slackResponseMessage = "Sms message can't be send!";
        }
		//uncomment once you have channelId and responseURL
		//sendSlackResponse(slackResponseMessage, channelId, responseUrl);
		
	
	}

	
	// Send the SMS message
		private boolean sendSmsMessage(String authToken, String message, String sender, String receiver) throws MalformedURLException, IOException {
			
			
		    URL url = new URL("https://connect.routee.net/sms");
		    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		    String requestBody = "{\"body\":\"" + message + "\", \"to\":\"" + receiver + "\", \"from\":\"" + sender + "\"}";
		    connection.setRequestMethod("POST");
		    connection.setRequestProperty("Authorization", "Bearer " + authToken);
		    connection.setRequestProperty("Content-Type", "application/json");
		    if (sender.length() > 11) {
		        sender = sender.substring(0,11);
		    }
		    connection.setDoOutput(true);
		    DataOutputStream wr = new DataOutputStream (connection.getOutputStream());
		    wr.writeBytes(requestBody);
		    wr.close();
		    int responseCode = connection.getResponseCode();
		    return responseCode == 200;
		}
		
		
	// Get authenticated through Routee's API
	private String routeeAuthenticate(String routeeAppId, String routeeAppSecret) throws MalformedURLException, IOException {
	    Base64.Encoder encoder = Base64.getEncoder();
	    String authString = routeeAppId + ":" + routeeAppSecret;
	    String token = encoder.encodeToString(authString.getBytes());
	    String accessToken = "";
	    URL url = new URL("https://auth.routee.net/oauth/token");
	    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	    connection.setRequestMethod("POST");
	    connection.setRequestProperty("Authorization", "Basic " + token);
	    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	    connection.setDoOutput(true);
	    DataOutputStream wr = new DataOutputStream (connection.getOutputStream());
	    wr.writeBytes("grant_type=client_credentials");
	    wr.close();
	    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	    String inputLine;
	    StringBuffer response = new StringBuffer();
	    while ((inputLine = in.readLine()) != null) {
	        response.append(inputLine);
	    }
	    in.close();
	    try {
	        @SuppressWarnings("deprecation")
			JSONParser parser = new JSONParser();
	        JSONObject jsonObject = (JSONObject)parser.parse(response.toString());
	        accessToken = (String) jsonObject.get("access_token");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return accessToken;
	}
	
	// Perform HTTP request validation and returning a proper message to Slack's user
	private void sendSlackResponse(String message, String channelId, String slackResponseUrl) throws MalformedURLException, IOException {
	    URL url = new URL(slackResponseUrl);
	    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	    String requestBody = "{\"channel\":\"" + channelId + "\", \"text\":\"" + message + "\"}";
	    String requestBodyLength = Integer.toString(requestBody.length());
	    connection.setRequestMethod("POST");
	    connection.setRequestProperty("Content-Length", "" + requestBodyLength + "");
	    connection.setRequestProperty("Content-Type", "application/json");
	    connection.setDoOutput(true);
	    DataOutputStream wr = new DataOutputStream (connection.getOutputStream());
	    wr.writeBytes(requestBody);
	    wr.close();
	}
}

