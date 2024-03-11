//IT21189944
//Madusanka G.K.I
//Group 2.1
package com.sa.safetynet.weather;

public class WeatherServiceImpl implements WeatherService, EmergencyWeatherHelper{
	   private float temperature;
	    private float lightIntensity;
	    
    @Override  
    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }
    @Override
    public void setLightIntensity(float intensity) {
        this.lightIntensity = intensity;
    }
    
    @Override
    public float getLightIntensity() {
        return lightIntensity;
    }
	@Override
	public float getTemprature() {
		return temperature;
	}
	
	//Falcon
	@Override
	public void ACPowerOverride(String location, String command) {
		// TODO Auto-generated method stub
		System.out.println("AC power " + command + " at "+location);
	}
	@Override
	public void LightPowerOverride(String location, String command) {
		// TODO Auto-generated method stub
		System.out.println("Heat power " + command + " at "+location);
	}
	@Override
	public void HeatPowerOverride(String location, String command) {
		// TODO Auto-generated method stub
		System.out.println("Light power " + command + " at "+location);
	}

}
