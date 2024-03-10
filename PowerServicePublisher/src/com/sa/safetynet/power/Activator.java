package com.sa.safetynet.power;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import com.sa.safetynet.emergency.service.EmergServiceInterface;

import weatherpublisher.WeatherService;

public class Activator implements BundleActivator {
	ServiceRegistration serviceRegistration;
	ServiceRegistration emergServiceRegistration;
	ServiceReference serviceReference;
	
	
	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Power Publisher starting");
		
		PowerService powerService = new PowerServiceImpl();
		EmergencyHelper emrgPowerService = new PowerServiceImpl();
		
		serviceRegistration = bundleContext.registerService(PowerService.class.getName(), powerService, null);
		emergServiceRegistration = bundleContext.registerService(EmergencyHelper.class.getName(), emrgPowerService, null);
		
		serviceReference = bundleContext.getServiceReference(WeatherService.class.getName());
		WeatherService weatherService = (WeatherService)bundleContext.getService(serviceReference);
		System.out.println(weatherService.Sunshining());
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Power Publish Stop");
		serviceRegistration.unregister();
		emergServiceRegistration.unregister();
	}

}
