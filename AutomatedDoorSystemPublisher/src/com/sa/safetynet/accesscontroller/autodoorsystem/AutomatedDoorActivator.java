package com.sa.safetynet.accesscontroller.autodoorsystem;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import com.sa.safetynet.alert.GetAlertInterface;
import com.sa.safetynet.alert.SetAlertInterface;

public class AutomatedDoorActivator implements BundleActivator {

	ServiceRegistration autoDoorSystemRegistration;
	ServiceRegistration emergDoorRegistration;
	
	//GetService from AlertEmergencySystem
	ServiceReference emergAlertService;
	
	public static GetAlertInterface checkEmergency;
	
	public void start(BundleContext context) throws Exception {
		System.out.println("Initiating Automated Door System...");
		
		AutomatedDoorService autoDoorService = new AutomatedDoorServiceImpl();
		autoDoorSystemRegistration = context.registerService(AutomatedDoorService.class.getName(), autoDoorService, null);
		
		EmergencyDoorHelper emergDoorService = new AutomatedDoorServiceImpl();
		emergDoorRegistration = context.registerService(EmergencyDoorHelper.class.getName(), emergDoorService, null);
		
		//Subscribe to Alert service
		emergAlertService = context.getServiceReference(GetAlertInterface.class.getName());
		GetAlertInterface getAlertService = (GetAlertInterface) context.getService(emergAlertService);
		
		checkEmergency = getAlertService;
		
		System.out.println("Automated Door System Initiated!!!");
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Aborting Automated Door System...");
		autoDoorSystemRegistration.unregister();
		emergDoorRegistration.unregister();
		
		context.ungetService(emergAlertService);
		
		System.out.println("Automated Door System Aborted!!!");
	}
}
