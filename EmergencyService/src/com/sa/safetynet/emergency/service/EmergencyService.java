package com.sa.safetynet.emergency.service;

import java.util.Scanner;

/*
 * IT21223594 - Thalangama T.P - Y3S2-WE-2.1
 * 
 */
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.sa.safetynet.power.EmergencyHelper;

public class EmergencyService implements EmergServiceInterface {
	
	String emrgType;
	String location;
	boolean isEmerg = false;
	
	public void overrideDoorLocks(String command) {
		// TODO Auto-generated method stub
		if(command.equals("off")) {
			//Access & invoke DoorLockOverride(String command) in Door Lock module
			EmergencyServiceActivator.overrideDoor.DoorLockOverride("active", location);
		}
		else if(command.equals("on")) {
			//Access & invoke DoorLockOverride(String command) in Door Lock module
			EmergencyServiceActivator.overrideDoor.DoorLockOverride("inactive", location);
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
		EmergencyServiceActivator.emergWeather.ACPowerOverride(location, command);
	}

	
	public void overrideHeatControll(String command) {
		// TODO Access & invoke HeatPowerOverride(String location, String command) in weather module
		EmergencyServiceActivator.emergWeather.HeatPowerOverride(location, command);
	}

	
	public void overrideLightControll(String command) {
		// TODO Access & invoke LightPowerOverride(String location, String command) in weather module
		EmergencyServiceActivator.emergWeather.LightPowerOverride(location, command);
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
	
	
	public void emergencyState() {
		// TODO Auto-generated method stub
		
		EmergencyServiceActivator.setAlert.setEmergAlert(isEmerg);
	}

	@Override
	public void activateEmergencyProtocol(String location) {
		Scanner sc = new Scanner(System.in);
		// TODO Auto-generated method stub
		this.location = location;
		isEmerg = true;
		emergencyState();
		System.out.println(emrgType + " emergency detected in " + location);
		
		if(emrgType.equals("fire")) {
			
			activateFireAlarm("on");
			switchRedLights("on");
			overrideACControll("off");
			overrideHeatControll("off");
			overrideLightControll("off");
			overridePowerToFireSuppression("on");
			overrideDoorLocks("off");
			
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			String emgAvailability;
			do {
			    System.out.println("Still Working on emergency");
			    System.out.println("Is Emergency still available in " + location + "?");
			    emgAvailability = sc.next();
			    if (emgAvailability.equals("no")) {
			        this.switchOffEmergencySystem();
			    }
			} while (emgAvailability.equals("yes"));
			
		}else if(emrgType.equals("gas")) {
			
			activateFireAlarm("on");
			switchRedLights("on");
			overrideACControll("off");
			overrideHeatControll("off");
			overrideLightControll("off");
			overridePowerToVentilationSystem("on");
			overrideDoorLocks("off");
			
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			String emgAvailability;
			do {
			    System.out.println("Still Working on emergency");
			    System.out.println("Is Emergency still available in " + location + "?");
			    emgAvailability = sc.next();
			    if (emgAvailability.equals("no")) {
			        this.switchOffEmergencySystem();
			    }
			} while (emgAvailability.equals("yes"));
		}else {
			System.out.println("Emergency Type is not recognizable!!");
		}
		
		sc.close();
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
		overrideACControll("on");
		overrideHeatControll("on");
		overrideLightControll("on");
		isEmerg = false;
		emergencyState();
		
	}


}
