package com.sa.safetynet.power.alert;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class PowerAlertActivator implements BundleActivator {
	ServiceRegistration<?> serviceRegistration;
	
	public void start(BundleContext context) throws Exception {
		PowerAlert powerAlert = new PowerAlertImpl();
		serviceRegistration = context.registerService(PowerAlert.class.getName(), powerAlert, null);
	}

	public void stop(BundleContext context) throws Exception {
		serviceRegistration.unregister();
		System.out.println("Power Alert deactivated!");
	}

}