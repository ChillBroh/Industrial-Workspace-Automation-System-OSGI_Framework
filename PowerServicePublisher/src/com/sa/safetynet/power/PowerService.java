package com.sa.safetynet.power;

public interface PowerService {
	public void startPowerService();
	
	public boolean powerOnStatus(String status);
	
	public float powerConsumption();
	
	public boolean startSolarPowerGeneration(String weather, String location);
	
	public void stopPowerService();
}
