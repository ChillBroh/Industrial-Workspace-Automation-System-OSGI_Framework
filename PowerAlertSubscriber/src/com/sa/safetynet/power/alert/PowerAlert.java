package com.sa.safetynet.power.alert;

public interface PowerAlert {
	public void displayAlert(String message);
	
	public void displayAlert(String name, String message, String location);
}