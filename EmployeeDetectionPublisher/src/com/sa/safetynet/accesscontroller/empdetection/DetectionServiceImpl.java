package com.sa.safetynet.accesscontroller.empdetection;

import java.util.HashMap;
import java.util.Map;

public class DetectionServiceImpl implements DetectionService{
	private Map<String, String> userTable = new HashMap<>();

	public DetectionServiceImpl() {
		
		createEmpTable();
	}
	
	
	public void createEmpTable() {
		userTable.put("Tharushi", "123");
		userTable.put("Ishara", "abc");
		userTable.put("Umesha", "678");
		userTable.put("Jhon", "stv");
		
	}

	@Override
	public boolean checkValidity(String id, String password) {
		
	if (userTable.containsKey(id)) {
            String Password = userTable.get(id);
            if (password.equals(Password)) {
            	return true; 
            }else {
            	return false;
            }
	}else {
		return false;
	}
		
	}

}
