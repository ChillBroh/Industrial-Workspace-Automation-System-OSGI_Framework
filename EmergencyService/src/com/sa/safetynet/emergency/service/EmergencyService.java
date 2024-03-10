package com.sa.safetynet.emergency.service;

/*
 * IT21223594 - Thalangama T.P - Y3S2-WE-2.1
 * 
 */
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.sa.safetynet.power.EmergencyHelper;

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
		//Access & invoke FireSuppresionPower(String command,String location) in power module
		EmergencyServiceActivator.emergPower.FireSuppresionPower( location, command);
		
	}

	
	public void overridePowerToVentilationSystem(String command) {
		//Access & invoke EmergencyVentilationPower(String command, String location) in power module
		EmergencyServiceActivator.emergPower.EmergencyVentilationPower(location, command);
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
		EmergencyServiceActivator.emergPower.AlarmLightPower(location, command);
	}
	
	public void activateFireAlarm(String command) {
		//Access & invoke EmergencyAlarmPower(String command, String location) in power module
		EmergencyServiceActivator.emergPower.EmergencyAlarmPower(location, command);
	
		
	}

	@Override
	public void sendNotification(String emrgType) {
		// TODO Auto-generated method stub
		
		this.emrgType = emrgType;
		System.out.println("Emregency System Activated!! Emergency type : " + emrgType);
			
	}
	
	@Override
	public boolean isEmergency() {
		// TODO Auto-generated method stub
		return isEmerg;
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


}
