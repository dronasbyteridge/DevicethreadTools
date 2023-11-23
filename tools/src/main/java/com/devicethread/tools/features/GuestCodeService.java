package com.devicethread.tools.features;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import com.devicethread.tools.utils.LockCodeConstants;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class GuestCodeService {

	public StringBuilder getLockCodeDetails(String deviceId, String token) {

		String apiUrl = "https://api.smartthings.com/v1/devices/" + deviceId
				+ "/components/main/capabilities/lockCodes/status";
		HttpClient httpClient = HttpClients.createDefault();

		// Create an HTTP GET request
		HttpGet getRequest = new HttpGet(apiUrl);

		// Set headers for the request
		getRequest.setHeader("Authorization", "Bearer " + token);
		getRequest.setHeader(HttpHeaders.ACCEPT, "application/json");
		getRequest.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
		try {
			// Execute the request and get the response
			HttpResponse response = httpClient.execute(getRequest);

			// Read the response content
			BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line;
			StringBuilder responseContent = new StringBuilder();

			while ((line = reader.readLine()) != null) {
				responseContent.append(line);
			}

			reader.close();
			return responseContent;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public StringBuilder cleanLockCodes(String deviceId, String index, String token) {

		
		String apiUrl = "https://api.smartthings.com/v1/devices/"+deviceId+"/commands";
		
		String requestBody = "[\n" + "    {\n" + "        \"component\": \"main\",\n"
				+ "        \"capability\": \"lockCodes\",\n" + "        \"command\": \"deleteCode\",\n"
				+ "        \"arguments\": [" + index + "]\n" + "    }\n" + "]";
		
		HttpClient httpClient = HttpClients.createDefault();

		// Create an HTTP POST request

		HttpPost postRequest = new HttpPost(apiUrl);

		// Set headers for the request
		postRequest.setHeader("Authorization", "Bearer " + token);
		postRequest.setHeader(HttpHeaders.ACCEPT, "application/json");
		postRequest.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
		try {
			StringEntity entity = new StringEntity(requestBody);
			postRequest.setEntity(entity);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			// Execute the request and get the response
			HttpResponse response = httpClient.execute(postRequest);

			// Read the response content
			BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line;
			StringBuilder responseContent = new StringBuilder();

			while ((line = reader.readLine()) != null) {
				responseContent.append(line);
			}

			reader.close();
			return responseContent;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public StringBuilder setLockCodes(String requestBody, String deviceId) {

		
		String apiUrl = "https://api.smartthings.com/v1/devices/"+deviceId+"/commands";
		
		
		HttpClient httpClient = HttpClients.createDefault();

		// Create an HTTP POST request

		HttpPost postRequest = new HttpPost(apiUrl);

		// Set headers for the request
		postRequest.setHeader("Authorization", "Bearer " + LockCodeConstants.token);
		postRequest.setHeader(HttpHeaders.ACCEPT, "application/json");
		postRequest.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
		try {
			StringEntity entity = new StringEntity(requestBody);
			postRequest.setEntity(entity);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			// Execute the request and get the response
			HttpResponse response = httpClient.execute(postRequest);

			// Read the response content
			BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line;
			StringBuilder responseContent = new StringBuilder();

			while ((line = reader.readLine()) != null) {
				responseContent.append(line);
			}

			reader.close();
			return responseContent;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
