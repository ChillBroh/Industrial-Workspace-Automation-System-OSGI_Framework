package com.sa.safetynet.power.solar;

import java.time.LocalTime;

import com.sa.safetynet.power.alert.PowerAlert;
import com.sa.safetynet.power.alert.PowerAlertImpl;
import com.sa.safetynet.weather.WeatherService;
import com.sa.safetynet.weather.WeatherServiceImpl;

public class SolarServiceImpl implements SolarService{
	private final WeatherService weatherService = new WeatherServiceImpl();
	private final PowerAlert powerAlert = new PowerAlertImpl();
	private float lightIntensity;
	private float contributionPercent = 0;
	private LocalTime startTime;
	private LocalTime endTime;
	
	@Override
	public void activateSolar(String command) {
		if(command.equalsIgnoreCase("On")) {
			System.out.println("Checking for light intesty and weather conditions...");
			
			lightIntensity = weatherService.getLightIntensity();
			if(lightIntensity > 10){
				startTime = LocalTime.now();
				setSolarStartTime(startTime);
				powerAlert.displayAlert("Solar System ativated at : " + startTime);
				if(lightIntensity > 20) {
					setContributionPercent(0.5f);
					powerAlert.displayAlert("Contributing to 50% of power generation...");
				}else {
					powerAlert.displayAlert("Contributing to 25% of power generation...");
					setContributionPercent(0.25f);
				}
			}else {
				System.out.println("Light Intensity is low...Solar power generation aborting...");
			}			
		}else {
			endTime = LocalTime.now();
			setSolarEndTime(endTime);
			powerAlert.displayAlert("Solar power generation aborting...");
		}
	}

	@Override
	public void setContributionPercent(float percent) {
		contributionPercent = percent;
	}

	@Override
	public float getContributionPercent() {
		return contributionPercent;
	}

	@Override
	public void setSolarStartTime(LocalTime time) {
		startTime = time;		
	}

	@Override
	public LocalTime getSolarStartTime() {
		return startTime;
	}

	@Override
	public void setSolarEndTime(LocalTime time) {
		endTime = time;
	}

	@Override
	public LocalTime getSolarEndTime() {
		return endTime;
	}
}