package com.devicethread.tools.features;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.devicethread.tools.features.models.locks.CodeParser;
import com.devicethread.tools.utils.LockCodeConstants;
import com.devicethread.tools.utils.LockCodeUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

public class GuestCodeRepositories {

	


	public CodeParser getLockCodeDetails(String deviceId, String token) {
		StringBuilder response=new GuestCodeService().getLockCodeDetails(deviceId, token);
		if(response!=null)
		{
			Gson gson = new Gson();

            // Parse the JSON string into your Java class
            CodeParser codeInfo = gson.fromJson(response.toString(), CodeParser.class);
            return codeInfo;
		}
		else
		{
			return null;
		}
		
	}
	
	public String cleanLockCodeDetails(String deviceId,String index, String token) {
		
		
		StringBuilder response=new GuestCodeService().cleanLockCodes(deviceId,index, token);
		return response.toString();
		
	}
	
	public void setCodesForDevices(String [] codes,JSONArray deviceIds,int totalCodeCount)
	{
	
		for(Object deviceId:deviceIds)
		{
			int indexPosition=0;
		StringBuilder response=new GuestCodeService().getLockCodeDetails(deviceId.toString(), LockCodeConstants.token);
		if(response!=null)
		{
			Gson gson = new Gson();

            // Parse the JSON string into your Java class
            CodeParser codeInfo = gson.fromJson(response.toString(), CodeParser.class);
            String[] indices = new LockCodeUtils().getAllLockCodeIndices(codeInfo.getLockCodes().getValue());
            
            for(int k=0;k<indices.length;k++)
            {
            	System.out.println(indices[k]);
            }
            for (int i = 1; i <= totalCodeCount; i++) {
				boolean exist = false;
 
				for (int j = 0; j < indices.length; j++) {
					if (i == Integer.parseInt(indices[j].trim())) {
 
						exist = true;
 
					}
				}
				if(!exist && indexPosition<codes.length)
				{
					String codeNumber = codes[indexPosition];
					indexPosition++;
					String codeValue = "code_" + codeNumber;
					System.out.println("setting code::"+codeValue);
					String requestBody = "[\n" + "    {\n" + "        \"component\": \"main\",\n"
							+ "        \"capability\": \"lockCodes\",\n" + "        \"command\": \"setCode\",\n"
							+ "        \"arguments\": [\n" + "            " + i + ", \"" + codeNumber + "\", \""
							+ codeValue + "\"\n" + "        ]\n" + "    }\n" + "]";
					System.out.println("requestBody =-->" + requestBody);
					StringBuilder res=new GuestCodeService().setLockCodes(requestBody, deviceId.toString()) ;
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
            }
            
		}
		}
	}
	
	
	public void createInsertQuery(String [] codes,JSONArray deviceIds,int totalCodeCount)
	{
	
		for(Object deviceId:deviceIds)
		{
			int indexPosition=0;
		StringBuilder response=new GuestCodeService().getLockCodeDetails(deviceId.toString(), LockCodeConstants.token);
		if(response!=null)
		{
			Gson gson = new Gson();

            // Parse the JSON string into your Java class
            CodeParser codeInfo = gson.fromJson(response.toString(), CodeParser.class);
            String[] indices = new LockCodeUtils().getAllLockCodeIndices(codeInfo.getLockCodes().getValue());
            
            for(int k=0;k<indices.length;k++)
            {
            	System.out.println(indices[k]);
            }
            for (int i = 1; i <= totalCodeCount; i++) {
				boolean exist = false;
 
				for (int j = 0; j < indices.length; j++) {
					if (i == Integer.parseInt(indices[j].trim())) {
 
						exist = true;
 
					}
				}
				if(!exist && indexPosition<codes.length)
				{
					String codeNumber = codes[indexPosition];
					indexPosition++;
					String codeValue = "code_" + codeNumber;
					System.out.println("setting code::"+codeValue);
					String query = "INSERT INTO public.\"LockCodes\"\r\n"
							+ "			(\"deviceId\", code, \"lockCodeLabel\", \"lockCodeIndex\", \"propertyId\")\r\n"
							+ "			VALUES(\'" + deviceId + "\',  \'" + codeNumber + "\', \'" + codeValue + "\', \'"
							+ indexPosition + "\',  \'c3e56344-4f25-450d-8ef6-363705b278a7\');";
					
					System.out.println(query);


				}
            }
            
		}
		}
	}
}
