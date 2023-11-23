package com.devicethread.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;

import com.devicethread.tools.features.GuestCodeReport;
import com.devicethread.tools.features.GuestCodeRepositories;
import com.devicethread.tools.features.models.locks.CodeParser;
import com.devicethread.tools.utils.LockCodeConstants;
import com.devicethread.tools.utils.LockCodeList;
import com.devicethread.tools.utils.LockCodeUtils;

public class Main {

	public static void main(String[] args) {

		// 0 generate guest code report
		// 1 generate staff code report
		// 2 generate master code report
		 new GuestCodeReport().generateGuestCodeReportForSingleDevice(0);

		List<Map<String, String[]>> data = new ArrayList<Map<String, String[]>>();
		

		Map<String, String[]> deviceData = new HashMap<String, String[]>();
		deviceData.put("98b22919-f12f-4709-b98a-2e241ca7432f", LockCodeList.d4);
		deviceData.put("f9410b94-0251-4b07-8071-98e0edff66eb", LockCodeList.d5);
		deviceData.put("9f8a56dc-6dcc-4333-9dbd-6b832b36e1cc", LockCodeList.d6);
		deviceData.put("b308ae64-7475-4a44-9fef-2a6d3f53f269", LockCodeList.d7);
		deviceData.put("5ee17c19-b21b-4d75-9c7a-93a49efd5d49", LockCodeList.d8);
		

		data.add(deviceData);
		//new GuestCodeReport().cleanDevices(data);
		//new Main().createInsertQuery();
		//String [] codesToSet= { "1082" };
	//	new GuestCodeReport().setCodeForDevice(codesToSet , 243);

	}

	public void createInsertQuery() {
		String deviceIds[] = { "f5fa1ef1-b562-44b9-bb24-9404f070999b" };
		String staffCodes[] = { "5783", "4853", "2707" };
		GuestCodeRepositories guestCodeRepositories = new GuestCodeRepositories();
		for (String deviceId : deviceIds) {
			System.out.println(deviceId);
			CodeParser codeInfo = guestCodeRepositories.getLockCodeDetails(deviceId, LockCodeConstants.token);
			String[] indices = new LockCodeUtils().getLockCodeIndices(codeInfo.getLockCodes().getValue());
			String[] codeValue = new LockCodeUtils().getLockCodeLabels(codeInfo.getLockCodes().getValue());
			for (int j = 0; j < codeValue.length; j++) {
				if (codeValue[j].trim().split("_").length > 1) {
					String code = codeValue[j].trim().split("_")[1].trim();
					if (ArrayUtils.contains(staffCodes, code.trim())) {

						System.out.println("Code:" + code + ":index:" + indices[j]);

					}
				}

			}
		}
	}

}
