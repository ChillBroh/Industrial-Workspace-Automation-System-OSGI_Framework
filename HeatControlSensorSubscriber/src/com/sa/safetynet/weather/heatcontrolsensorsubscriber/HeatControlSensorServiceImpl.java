//IT21189944
//Madusanka G.K.I
//Group 2.1
package com.sa.safetynet.weather.heatcontrolsensorsubscriber;

public class HeatControlSensorServiceImpl implements HeatControlSensorService{
	private int numberOfHeatersOn = 0; 
	private int heatTemp = 0;
	private int finalHeatTemp = 24;
	@Override
	public void turnOnHeater(String[] areas, int temp) {
		this.heatTemp = temp;
		for (String area : areas) {
            System.out.println(area + " Heater Off!");
            numberOfHeatersOn++; 
        }
		
	}

	@Override
	public void turnOffHeater(String[] areas) {
		for (String area : areas) {
            System.out.println(area + "Heater On!");
            numberOfHeatersOn--; 
        }
		
	}

	@Override
	public int[] getTotalHeatersOn() {
	    int[] acOnArray = new int[]{numberOfHeatersOn,finalHeatTemp - heatTemp}; 
	    return acOnArray;
	}

}
