package com.sa.safetynet.power.solar;

import java.time.LocalTime;

public interface SolarService {
	public void activateSolar(String command);
	
	public void setContributionPercent(float percent);
	
	public float getContributionPercent();
	
	public void setSolarStartTime(LocalTime time);
	
	public LocalTime getSolarStartTime();
	
	public void setSolarEndTime(LocalTime time);
	
	public LocalTime getSolarEndTime();
}