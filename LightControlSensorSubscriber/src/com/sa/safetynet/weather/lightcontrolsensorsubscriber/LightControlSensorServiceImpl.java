//IT21189944
//Madusanka G.K.I
//Group 2.1
package com.sa.safetynet.weather.lightcontrolsensorsubscriber;

public class LightControlSensorServiceImpl implements LightControlSensorService {
	private int numberOfLightsOn = 0; 

    @Override
    public void turnOffLights(String[] areas) {
        for (String area : areas) {
            System.out.println(area + " lights Off!");
            numberOfLightsOn--; 
        }
    }

    @Override
    public void turnOnLights(String[] areas) {
        for (String area : areas) {
            System.out.println(area + " lights On!");
            numberOfLightsOn++; 
        }
    }

    @Override
    public int getTotalLightsOn() {
        return numberOfLightsOn;
    }

}
