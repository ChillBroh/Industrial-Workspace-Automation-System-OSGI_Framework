package com.sa.safetynet.power.solar;

import com.sa.safetynet.power.alert.PowerAlert;
import com.sa.safetynet.weather.WeatherService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

public class SolarServiceActivator implements BundleActivator {
	private SolarService solarService = new SolarServiceImpl();
	ServiceRegistration serviceRegistration;
	ServiceReference weatherServiceReference;
	ServiceReference alertServieReference;
	
	public void start(BundleContext context) throws Exception {
		serviceRegistration = context.registerService(SolarService.class.getName(), solarService, null);
		
		weatherServiceReference = context.getServiceReference(WeatherService.class.getName());
		WeatherService weatherService = (WeatherService) context.getService(weatherServiceReference);
		
		alertServieReference = context.getServiceReference(PowerAlert.class.getName());
		PowerAlert powerAlert = (PowerAlert) context.getService(alertServieReference);
		
		System.out.println("Starting Solar System...");
		solarService = new SolarServiceImpl();
		solarService.activateSolar("On");
	}

	public void stop(BundleContext context) throws Exception {
		
		context.ungetService(alertServieReference);
		context.ungetService(weatherServiceReference);
		serviceRegistration.unregister();
		
		System.out.println("Solar Service Publisher deactivated!");
		
	}
}