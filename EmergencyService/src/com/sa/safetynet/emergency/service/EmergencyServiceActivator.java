package com.sa.safetynet.emergency.service;

/*
 * IT21223594 - Thalangama T.P - Y3S2-WE-2.1
 * 
 */

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import com.sa.safetynet.power.EmergencyHelper;

public class EmergencyServiceActivator implements BundleActivator {

	ServiceRegistration<?> sensorServiceRegister;
	ServiceRegistration<?> moduleAlertServiceRegister;
	ServiceReference emergServiceReference;
	public static EmergencyHelper emergPower;
	
	public void start(BundleContext context) throws Exception {
		
		System.out.println("Emergency System is now online!");
		
		//Instantiating two types of EmergecnyService class for different type of subscribers
		EmergServiceInterface emrgService = new EmergencyService();
		EmergencyCheckInterface emrgNotify = new EmergencyService();
		
		//Registering Sensor services and Emergency check services to the OSGI service registry
		sensorServiceRegister = context.registerService(EmergServiceInterface.class.getName(), emrgService, null);
		moduleAlertServiceRegister = context.registerService(EmergencyCheckInterface.class.getName() , emrgNotify, null);
		
		//Getting emergency services available to Emergency service module from PowerServicePublisher module by accessing OSGI service registry
		emergServiceReference = context.getServiceReference(EmergencyHelper.class.getName());
		EmergencyHelper emergencyPowerServices = (EmergencyHelper)context.getService(emergServiceReference);
		
		// Assigning the received service instance to a static variable for direct access without the need for instantiating an object of this class.
		emergPower = emergencyPowerServices;
		
	}

	
	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Emergency System is now offline!");
		sensorServiceRegister.unregister();
		moduleAlertServiceRegister.unregister();
	}

}
