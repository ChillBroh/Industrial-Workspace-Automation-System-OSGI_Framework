package com.sa.safetynet.power;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

//import com.sa.safetynet.emergency.service.EmergServiceInterface;

//import weatherpublisher.WeatherService;

public class PowerServiceActivator implements BundleActivator {
	ServiceRegistration powerServiceRegistration;
	ServiceRegistration emergServiceRegistration;

	
	
	public void start(BundleContext context) throws Exception {
		
		System.out.println("Power Service is starting...");
		
		PowerService powerService = new PowerServiceImpl();
		EmergencyHelper emrgPowerService = new PowerServiceImpl();
		
		powerServiceRegistration = context.registerService(PowerService.class.getName(), powerService, null);
		emergServiceRegistration = context.registerService(EmergencyHelper.class.getName(), emrgPowerService, null);
		
		powerService.powerOnStatus("On");
		System.out.println("Power Service Started!!!");
	}


	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Power Service is aborting...");
		powerServiceRegistration.unregister();
		emergServiceRegistration.unregister();
		
	}

}

