package com.sa.safetynet.power;

public class PowerServiceImpl implements PowerService, EmergencyHelper{
	private boolean powerOn;
	
	@Override
	public void startPowerService() {
		System.out.println("Power System is starting...");
		powerOnStatus("on");
		System.out.println("Power System Started");
	}

	@Override
	public boolean powerOnStatus(String status) {
		if(status.equalsIgnoreCase("on")) {
			return true;
		}
		return false;
	}
	
	@Override
	public float powerConsumption() {
		return 1000.75f;
	}
	
	@Override
	public boolean startSolarPowerGeneration(String weather, String location) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void stopPowerService() {
		System.out.println("Power System is aborting...");
		powerOnStatus("off");
		System.out.println("Power System Stopped");
	}

	
	// Falcon
	@Override
	public void AlarmLightPower(String location, String command) {
		// TODO Auto-generated method stub

		if(command.equals("on")) {
			// TODO Access & invoke AlarmLightPower(String location, String command) in Power module
			System.out.println("Red Light power " + command + " at "+location);
		}
		else if(command.equals("off")) {
			// TODO Access & invoke AlarmLightPower(String location, String command) in Power module
			System.out.println("Red Light power " + command + " at "+location);
		}else {
			System.out.println("Invalid command at Switch Red Lights!");
		}
	}

	@Override
	public void FireSuppresionPower(String location, String command) {
		// TODO Auto-generated method stub
		System.out.println("FireSuppression Activated in " + location);
	}

	@Override
	public void EmergencyVentilationPower(String location, String command) {
		// TODO Auto-generated method stub
		System.out.println("Ventilation System Activated in " + location);
	}

	@Override
	public void EmergencyAlarmPower(String location, String command) {
		// TODO Auto-generated method stub
	if(command.equals("off")) {
			
			System.out.println("Fire Alarm power " + command + " at "+location);
		}
		else if(command.equals("on")) {
			
			System.out.println("Fire Alarms activated in " + location);
		}else {
			System.out.println("Invalid command at Activate Fire Alarm!");
		}
		
	}
}
