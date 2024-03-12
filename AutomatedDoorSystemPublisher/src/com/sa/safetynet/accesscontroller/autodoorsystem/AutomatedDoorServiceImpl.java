package com.sa.safetynet.accesscontroller.autodoorsystem;

import java.util.Timer;
import java.util.TimerTask;

public class AutomatedDoorServiceImpl implements AutomatedDoorService, EmergencyDoorHelper{

	@Override
	public void doorUnlock(String location) {
		
		System.out.println("Door Unlocked at " + location);
		// Create a Timer object
		Timer timer = new Timer();

        // Schedule a task to run after 30 seconds
        timer.schedule(new TimerTask() {
        	
            @Override
            public void run() {
                doorLock(location);
            }
        }, 30 * 1000);
		
		
	}
	
	@Override
	public void doorLock(String location) {
		
		//Should check whether access valid or not
		boolean emergency = AutomatedDoorActivator.checkEmergency.getEmergState();
		   
	    if(emergency == true) {
	    	
			   System.out.println("Emergency situation detected. Unlocking door immediately..!!");
			   doorUnlock(location);
	    }else {
	    	System.out.println("Door locked at " + location);
	    }
        
	}

	@Override
	public void DoorLockOverride(String command, String location) {
		// TODO Auto-generated method stub
		if(command.equals("active")) {
			
			System.out.println("An emergency situation arised. Unlocking doors immediately at "+ location);
			doorUnlock(location);
			
		}else if(command.equals("inactive")) {
			
			System.out.println("Emergency situation is de-escalated . Locking doors at "+ location);
			doorLock(location);
		}else {
			System.out.println("Command is not recognizable!!");
		}
	}



}
