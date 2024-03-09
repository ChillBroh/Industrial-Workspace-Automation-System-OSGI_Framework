package emergencyservice;

public class EmergencyService implements EmergServiceInterface {
	
	String emrgType;
	String location;
	
	
	public void OverrideDoorLocks(String command) {
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

	
	public void OverridePowerToFireSuppression(String command) {
		// TODO Access & invoke FireSuppresionPower(String command,String location) in power module
		
		System.out.println("FireSuppression Activated in " + location);
	}

	
	public void OverridePowerToVentilationSystem(String command) {
		// TODO Access & invoke EmergencyVentilationPower(String command, String location) in power module
		System.out.println("Ventilation System Activated in " + location);
	}

	
	public void OverrideACControll(String command) {
		// TODO Access & invoke ACPowerOverride(String location, String command) in weather module
		
		System.out.println("AC power " + command + " at "+location);
	}

	
	public void OverrideHeatControll(String command) {
		// TODO Access & invoke HeatPowerOverride(String location, String command) in weather module
		System.out.println("Heat power " + command + " at "+location);
	}

	
	public void OverrideLightControll(String command) {
		// TODO Access & invoke LightPowerOverride(String location, String command) in weather module
		System.out.println("Light power " + command + " at "+location);
	}


	public void SwitchRedLights(String command) {
		
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
	public void SendNotification(String emrgType) {
		// TODO Auto-generated method stub
		
		this.emrgType = emrgType;
		System.out.println("Emregency System Activated!! Emergency type : " + emrgType);
		
		
	}

	@Override
	public void ActivateEmergencyProtocol(String location) {
		// TODO Auto-generated method stub
		this.location = location;
		System.out.println(emrgType + " emergency detected in " + location);
		
		if(emrgType.equals("fire")) {
			
			ActivateFireAlarm("on");
			SwitchRedLights("on");
			OverridePowerToFireSuppression("on");
			OverrideDoorLocks("off");
			
		}else if(emrgType.equals("gas")) {
			
			ActivateFireAlarm("on");
			SwitchRedLights("on");
			OverridePowerToVentilationSystem("on");
			OverrideDoorLocks("off");
		}else {
			System.out.println("Emergency Type is not recognizable!!");
		}
		
		
	}

	
	public void ActivateFireAlarm(String command) {
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
	public void SwitchOffEmergencySystem() {
		// TODO Auto-generated method stub
		System.out.println("Emergency System is deactivated");
		ActivateFireAlarm("off");
		OverrideDoorLocks("on");
		SwitchRedLights("off");
		OverridePowerToFireSuppression("off");
		OverridePowerToVentilationSystem("off");
		OverrideACControll("off");
		OverrideHeatControll("off");
		OverrideLightControll("on");
		
	}

}
