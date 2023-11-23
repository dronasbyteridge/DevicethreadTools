package com.devicethread.tools.features;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.devicethread.tools.features.models.locks.CodeParser;
import com.devicethread.tools.utils.CsvUtils;
import com.devicethread.tools.utils.LockCodeConstants;
import com.devicethread.tools.utils.LockCodeUtils;

public class GuestCodeReport {

	public void generateGuestCodeReport(int report) {

		GuestCodeRepositories guestCodeRepositories = new GuestCodeRepositories();

		try {
			FileReader reader = new FileReader(LockCodeConstants.jsonPatPath);
			JSONObject jsonObject = new JSONObject(new JSONTokener(reader));

			// Get the array of device IDs
			JSONArray deviceIds = jsonObject.getJSONArray("deviceIds");

			List<LinkedHashMap<String, Object>> data = new ArrayList<>();
			for (Object deviceId : deviceIds) {
				System.out.println(deviceId);
				// data = new ArrayList<>();

				CodeParser codeInfo = guestCodeRepositories.getLockCodeDetails(deviceId.toString(),
						LockCodeConstants.token);
				String[] indices = new LockCodeUtils().getLockCodeIndices(codeInfo.getLockCodes().getValue());

				String[] codeValue = new LockCodeUtils().getLockCodeLabels(codeInfo.getLockCodes().getValue());
				int columnIndex = 0;
				LinkedHashMap<String, Object> rowMap = new LinkedHashMap<>();
				rowMap.clear();
				rowMap.put("DeviceId", deviceId.toString());
				for (int i = 0; i < codeValue.length; i++) {

					if (report == 0 && codeValue[i].trim().startsWith("code_")
							&& !ArrayUtils.contains(LockCodeConstants.mastercodes, codeValue[i].toString().trim())
							&& !ArrayUtils.contains(LockCodeConstants.staffCodes, codeValue[i].toString().trim())) {
						String code = codeValue[i].trim().split("_")[1].trim();
						String columnName = "Code" + columnIndex++;
						rowMap.put(columnName, code);
						rowMap.put("Index"+indices[i], indices[i]);
						System.out.println("inside guest");
					}

					else if (report == 2 && codeValue[i].trim().startsWith("code_")
							&& ArrayUtils.contains(LockCodeConstants.mastercodes, codeValue[i].toString().trim())) {
						String code = codeValue[i].trim().split("_")[1].trim();
						String columnName = "Code" + columnIndex++;
						rowMap.put(columnName, code);
						rowMap.put("Index"+indices[i], indices[i]);
						System.out.println("inside master");

					} else if (report == 1 && codeValue[i].trim().startsWith("code_")
							&& ArrayUtils.contains(LockCodeConstants.staffCodes, codeValue[i].toString().trim())) {
						String code = codeValue[i].trim().split("_")[1].trim();
						String columnName = "Code" + columnIndex++;
						rowMap.put(columnName, code);
						rowMap.put("Index"+indices[i], indices[i]);
						System.out.println("inside staff");

					}

				}
				data.add(rowMap);

			}
			String fileName = "guest_report";
			switch (report) {
			case 0:
				fileName = "guest_report";
				break;
			case 1:
				fileName = "staff_report";
				break;
			case 2:
				fileName = "master_report";
				break;
			}

			String sheetfilePath = "C:\\Users\\SSC\\" + fileName + ".csv";
			new CsvUtils().createCsv(sheetfilePath, data);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void generateGuestCodeReportForSingleDevice(int report) {

		GuestCodeRepositories guestCodeRepositories = new GuestCodeRepositories();

		try {
			FileReader reader = new FileReader(LockCodeConstants.jsonPatPath);
			JSONObject jsonObject = new JSONObject(new JSONTokener(reader));

			// Get the array of device IDs
			JSONArray deviceIds = jsonObject.getJSONArray("deviceIds");

			List<LinkedHashMap<String, Object>> data = new ArrayList<>();
			for (Object deviceId : deviceIds) {
				System.out.println(deviceId);
				// data = new ArrayList<>();

				CodeParser codeInfo = guestCodeRepositories.getLockCodeDetails(deviceId.toString(),
						LockCodeConstants.token);
				String[] indices = new LockCodeUtils().getLockCodeIndices(codeInfo.getLockCodes().getValue());

				String[] codeValue = new LockCodeUtils().getLockCodeLabels(codeInfo.getLockCodes().getValue());
				int columnIndex = 0;
				
				for (int i = 0; i < codeValue.length; i++) {
					LinkedHashMap<String, Object> rowMap = new LinkedHashMap<>();
					rowMap.clear();
					rowMap.put("DeviceId", deviceId);
					//rowMap.put("DeviceId", deviceId.toString());
					if (report == 0 && codeValue[i].trim().startsWith("code_")
							&& !ArrayUtils.contains(LockCodeConstants.mastercodes, codeValue[i].toString().trim())
							&& !ArrayUtils.contains(LockCodeConstants.staffCodes, codeValue[i].toString().trim())) {
						String code = codeValue[i].trim().split("_")[1].trim();
						String columnName = "Code" + columnIndex++;
						rowMap.put(columnName, code);
						rowMap.put("Index"+indices[i], indices[i]);
						System.out.println("inside guest");
					}

					else if (report == 2 && codeValue[i].trim().startsWith("code_")
							&& ArrayUtils.contains(LockCodeConstants.mastercodes, codeValue[i].toString().trim())) {
						String code = codeValue[i].trim().split("_")[1].trim();
						String columnName = "Code" + columnIndex++;
						rowMap.put(columnName, code);
						rowMap.put("Index"+indices[i], indices[i]);
						System.out.println("inside master");

					} else if (report == 1 && codeValue[i].trim().startsWith("code_")
							&& ArrayUtils.contains(LockCodeConstants.staffCodes, codeValue[i].toString().trim())) {
						String code = codeValue[i].trim().split("_")[1].trim();
						String columnName = "Code" + columnIndex++;
						rowMap.put(columnName, code);
						rowMap.put("Index"+indices[i], indices[i]);
						System.out.println("inside staff");

					}

					data.add(rowMap);
				}
				

			}
			String fileName = "guest_report";
			switch (report) {
			case 0:
				fileName = "guest_report";
				break;
			case 1:
				fileName = "staff_report";
				break;
			case 2:
				fileName = "master_report";
				break;
			}

			String sheetfilePath = "C:\\Users\\SSC\\" + fileName + ".csv";
			new CsvUtils().createCsv(sheetfilePath, data);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void cleanDevices(List<Map<String, String[]>> data) {

		GuestCodeRepositories guestCodeRepositories = new GuestCodeRepositories();

		for (Map<String, String[]> dataItem : data) {
			for (String key : dataItem.keySet()) {
				System.out.println("Deleting for device id::"+key);
				CodeParser codeInfo = guestCodeRepositories.getLockCodeDetails(key, LockCodeConstants.token);
				String[] indices = new LockCodeUtils().getLockCodeIndices(codeInfo.getLockCodes().getValue());

				String[] codeValue = new LockCodeUtils().getLockCodeLabels(codeInfo.getLockCodes().getValue());
				for (int j = 0; j < codeValue.length; j++) {
					if (codeValue[j].trim().split("_").length > 1) {
						String code = codeValue[j].trim().split("_")[1].trim();

						if (!ArrayUtils.contains(dataItem.get(key), code.trim())
								&& !ArrayUtils.contains(LockCodeConstants.mastercodes, codeValue[j].trim())
								&& !ArrayUtils.contains(LockCodeConstants.staffCodes, codeValue[j].trim())
								&& !ArrayUtils.contains(LockCodeConstants.codeToExclude, indices[j].trim())) {
							System.out.println("Deleting code and index:::"+code.toString() + ":" + indices[j].toString());

							
							
							
							  String response = guestCodeRepositories.cleanLockCodeDetails(key,
							  indices[j].trim(), LockCodeConstants.token);
							  System.out.println(response.toString()); try { Thread.sleep(10000); } catch
							  (InterruptedException e) { // TODO Auto-generated catch block
							  e.printStackTrace(); }
							 
							 
							 

						}
					}
				}

				// }
			}
		}

	}

	public void setCodeForDevice(String[] codes,int totalCodeCount)
	{
		FileReader reader = null;
		try {
			reader = new FileReader(LockCodeConstants.jsonPatPath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject jsonObject = new JSONObject(new JSONTokener(reader));

		// Get the array of device IDs
		JSONArray deviceIds = jsonObject.getJSONArray("deviceIds");
		new GuestCodeRepositories().setCodesForDevices(codes, deviceIds, totalCodeCount);
	}
}
