package com.sa.safetynet.accesscontroller.notificationsystem;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class NotificationSystemActivator implements BundleActivator {

	ServiceRegistration autoDoorSystemRegistration;

	public void start(BundleContext context) throws Exception {
		System.out.println("Initiating Notification System...");
		
		NotificationSystem autoDoorService = new NotificationSystemImpl();
		autoDoorSystemRegistration = context.registerService(NotificationSystem.class.getName(), autoDoorService, null);
		
		System.out.println("Notification System Initiated!!!");
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Aborting Notification System...");
		autoDoorSystemRegistration.unregister();
		System.out.println("Notification System Aborted!!!");
	}
}
