package com.sa.safetynet.power.alert;

public class PowerAlertImpl implements PowerAlert{

	@Override
	public void displayAlert(String name, String message, String location) {
		System.out.println("Hi " + name + " " + message + " in " + location);
	}

	@Override
	public void displayAlert(String message) {
		System.out.println(message);
	}
}