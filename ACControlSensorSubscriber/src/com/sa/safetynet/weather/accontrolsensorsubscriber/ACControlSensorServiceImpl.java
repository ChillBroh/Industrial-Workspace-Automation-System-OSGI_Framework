//IT21189944
//Madusanka G.K.I
//Group 2.1
package com.sa.safetynet.weather.accontrolsensorsubscriber;

public class ACControlSensorServiceImpl implements ACControlSensorService {
	private int numberOfACsOn = 0;
	private int acTemp = 0;
	@Override
	public void turnOnAC(String[] areas, int temp) {
		this.acTemp = temp;
		for (String area : areas) {
            System.out.println(area + " AC On!");
            numberOfACsOn++;
        }

	}

	@Override
	public void turnOffAC(String[] areas) {
		for (String area : areas) {
            System.out.println(area + "AC On!");
            numberOfACsOn--;
        }

	}

	@Override
	public int[] getTotalAcOn() {
	    int[] acOnArray = new int[]{numberOfACsOn,acTemp};
	    return acOnArray;
	}
}
