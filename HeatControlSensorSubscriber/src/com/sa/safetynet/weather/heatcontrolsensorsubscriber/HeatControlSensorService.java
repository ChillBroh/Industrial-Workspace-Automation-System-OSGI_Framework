//IT21189944
//Madusanka G.K.I
//Group 2.1
package com.sa.safetynet.weather.heatcontrolsensorsubscriber;

public interface HeatControlSensorService {

	public void turnOnHeater(String[] areas, int temp);
    public void turnOffHeater(String[] areas);
    public int[] getTotalHeatersOn();
}
