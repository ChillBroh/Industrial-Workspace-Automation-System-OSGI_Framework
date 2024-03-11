package com.sa.safetynet.accesscontroller.notificationsystem;

public class NotificationSystemImpl implements NotificationSystem{

	@Override
	public void SendNotification(String location) {
		
		System.out.println("Opening Door at " + location);
		
	}

	

}
