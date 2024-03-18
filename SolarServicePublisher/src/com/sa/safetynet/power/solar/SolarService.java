package com.sa.safetynet.power.solar;

import java.time.LocalTime;

import com.sa.safetynet.weather.WeatherService;

public interface SolarService {
	public void activateSolar(WeatherService service, String command);
	
	public void deActivateSolar(String command);
	
	public void setContributionPercent(float percent);
	
	public float getContributionPercent();
	
	public void setSolarStartTime(LocalTime time);
	
	public LocalTime getSolarStartTime();
	
	public void setSolarEndTime(LocalTime time);
	
	public LocalTime getSolarEndTime();
}