
//IT21189944
//Madusanka G.K.I
//Group 2.1
package com.sa.safetynet.weather.accontrolsensorsubscriber;

public interface ACControlSensorService {
	public void turnOnAC(String[] areas, int temp);
    public void turnOffAC(String[] areas);
    public int[] getTotalAcOn();
}
