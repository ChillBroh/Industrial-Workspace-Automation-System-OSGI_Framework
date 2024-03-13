package com.sa.safetynet.power.monitor;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.sa.safetynet.power.alert.PowerAlert;
import com.sa.safetynet.power.solar.SolarService;

public class PowerMonitorActivator implements BundleActivator {

	ServiceReference heatControlServiceReference;
	
	ServiceReference acControlServiceReference;
	
	ServiceReference lightControlSensorServiceReference;
	
	ServiceReference solarServiceReference;
	
	ServiceReference alertServiceReference;


	public void start(BundleContext context) throws Exception {
		
		heatControlServiceReference = context.getServiceReference(HeatControlSensorService.class.getName());
		HeatControlSensorService heatControlSensorService = (HeatControlSensorService) context.getService(heatControlServiceReference);
		
		acControlServiceReference = context.getServiceReference(ACControlSensorService.class.getName());
		ACControlSensorService acControlSensorService = (ACControlSensorService) context.getService(acControlServiceReference);
		
		lightControlSensorServiceReference = context.getServiceReference(LightControlSensorService.class.getName());
		LightControlSensorService lightControlSensorService = (LightControlSensorService) context.getService(lightControlSensorServiceReference);
		
		solarServiceReference = context.getServiceReference(SolarService.class.getName());
		SolarService solarService = (SolarService) context.getService(solarServiceReference);
		
		alertServiceReference = context.getServiceReference(PowerAlert.class.getName());
		PowerAlert powerAlert = (PowerAlert) context.getService(alertServiceReference);
		
		PowerMonitor powerMonitor = new PowerMonitorImpl();
		powerMonitor.generateReport();
		
//		heatControlServiceReference = context.getServiceReference(PowerService.class.getName());
//		heatControlServiceReference = context.getServiceReference(HeatControlSensorService.class.getName());
//		HeatControlSensorService heatControlSensorService = (HeatControlSensorService) context.getService(heatControlServiceReference);
//		
//		acControlServiceReference = context.getServiceReference(ACControlSensorService.class.getName());
//		ACControlSensorService acControlSensorService = (ACControlSensorService) context.getService(acControlServiceReference);
//		
//		lightControlSensorServiceReference = context.getServiceReference(LightControlSensorService.class.getName());
//		LightControlSensorService lightControlSensorService = (LightControlSensorService) context.getService(lightControlSensorServiceReference);

		
	}

	public void stop(BundleContext context) throws Exception {
		
		context.ungetService(heatControlServiceReference);
		
		context.ungetService(acControlServiceReference);
		
		context.ungetService(lightControlSensorServiceReference);
		
		context.ungetService(solarServiceReference);
		
		context.ungetService(alertServiceReference);
		
		System.out.println("Power monitor subscriber deactivated!");


	}

}