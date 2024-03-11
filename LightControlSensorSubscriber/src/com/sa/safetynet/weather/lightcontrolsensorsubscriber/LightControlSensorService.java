//IT21189944
//Madusanka G.K.I
//Group 2.1
package com.sa.safetynet.weather.lightcontrolsensorsubscriber;

public interface LightControlSensorService {
    public void turnOffLights(String[] areas);

    public void turnOnLights(String[] areas);

    public int getTotalLightsOn();

}
