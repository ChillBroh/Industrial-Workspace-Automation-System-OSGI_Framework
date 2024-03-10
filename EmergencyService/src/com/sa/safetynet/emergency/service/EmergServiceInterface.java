package com.sa.safetynet.emergency.service;

/*
 * IT21223594 - Thalangama T.P - Y3S2-WE-2.1
 * 
 */
public interface EmergServiceInterface {
	public void sendNotification(String emrgType);
	public void activateEmergencyProtocol(String location);
	public void switchOffEmergencySystem();
	
}
