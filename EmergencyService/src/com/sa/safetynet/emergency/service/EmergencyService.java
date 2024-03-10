package com.sa.safetynet.emergency.service;

public class EmergencyService implements EmergServiceInterface, EmergencyCheckInterface {
	
	String emrgType;
	String location;
	boolean isEmerg;
	
	
	public void overrideDoorLocks(String command) {
		// TODO Auto-generated method stub
		if(command.equals("off")) {
			// TODO Access & invoke DoorLockOverride(String command) in Door Lock module
			System.out.println("Door lock protocols are overrided and unlocked in " + location);
		}
		else if(command.equals("on")) {
			// TODO Access & invoke DoorLockOverride(String command) in Door Lock module
			System.out.println("Door lock protocols are restored and locked in " + location);
		}else {
			System.out.println("Invalid command at Door lock override!");
		}
	}

	
	public void overridePowerToFireSuppression(String command) {
		// TODO Access & invoke FireSuppresionPower(String command,String location) in power module
		
		System.out.println("FireSuppression Activated in " + location);
	}

	
	public void overridePowerToVentilationSystem(String command) {
		// TODO Access & invoke EmergencyVentilationPower(String command, String location) in power module
		System.out.println("Ventilation System Activated in " + location);
	}

	
	public void overrideACControll(String command) {
		// TODO Access & invoke ACPowerOverride(String location, String command) in weather module
		
		System.out.println("AC power " + command + " at "+location);
	}

	
	public void overrideHeatControll(String command) {
		// TODO Access & invoke HeatPowerOverride(String location, String command) in weather module
		System.out.println("Heat power " + command + " at "+location);
	}

	
	public void overrideLightControll(String command) {
		// TODO Access & invoke LightPowerOverride(String location, String command) in weather module
		System.out.println("Light power " + command + " at "+location);
	}


	public void switchRedLights(String command) {
		
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
	public void sendNotification(String emrgType) {
		// TODO Auto-generated method stub
		
		this.emrgType = emrgType;
		System.out.println("Emregency System Activated!! Emergency type : " + emrgType);
		
		
	}

	@Override
	public void activateEmergencyProtocol(String location) {
		// TODO Auto-generated method stub
		this.location = location;
		isEmerg = true;
		
		System.out.println(emrgType + " emergency detected in " + location);
		
		if(emrgType.equals("fire")) {
			
			activateFireAlarm("on");
			switchRedLights("on");
			overridePowerToFireSuppression("on");
			overrideDoorLocks("off");
			
		}else if(emrgType.equals("gas")) {
			
			activateFireAlarm("on");
			switchRedLights("on");
			overridePowerToVentilationSystem("on");
			overrideDoorLocks("off");
		}else {
			System.out.println("Emergency Type is not recognizable!!");
		}
		
		
	}

	
	public void activateFireAlarm(String command) {
		// TODO Access & invoke EmergencyAlarmPower(String command, String location) in power module
		
		if(command.equals("off")) {
			
			System.out.println("Fire Alarm power " + command + " at "+location);
		}
		else if(command.equals("on")) {
			
			System.out.println("Fire Alarms activated in " + location);
		}else {
			System.out.println("Invalid command at Activate Fire Alarm!");
		}
		
		
	}

	@Override
	public void switchOffEmergencySystem() {
		// TODO Auto-generated method stub
		System.out.println("Emergency System is deactivated");
		activateFireAlarm("off");
		overrideDoorLocks("on");
		switchRedLights("off");
		overridePowerToFireSuppression("off");
		overridePowerToVentilationSystem("off");
		overrideACControll("off");
		overrideHeatControll("off");
		overrideLightControll("on");
		isEmerg = false;
		
	}


	@Override
	public boolean isEmergency() {
		// TODO Auto-generated method stub
		return isEmerg;
	}

}
