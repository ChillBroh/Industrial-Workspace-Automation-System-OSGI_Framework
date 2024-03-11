package com.sa.safetynet.weather;

public interface EmergencyWeatherHelper {
	public void ACPowerOverride(String location, String command);
	public void HeatPowerOverride(String location, String command);
	public void LightPowerOverride(String location, String command);
	
}
