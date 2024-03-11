package com.sa.safetynet.alert;

public class AlertController implements SetAlertInterface, GetAlertInterface {
	
	private static boolean isEmerg = false;
	@Override
	public boolean getEmergState() {
		// TODO Auto-generated method stub
		return isEmerg;
	}

	@Override
	public void setEmergAlert(boolean state) {
		// TODO Auto-generated method stub
		
		isEmerg = state;
	}

}
