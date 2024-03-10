package com.sa.safetynet.power;

public interface EmergencyHelper {
	public void AlarmLightPower(String location, String command);
	
	public void FireSuppresionPower(String location, String command);
	
	public void EmergencyVentilationPower(String location, String command);
	
	public void EmergencyAlarmPower(String location, String command);
}
