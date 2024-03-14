package com.sa.safetynet.emergency.service;

/*
 * IT21223594 - Thalangama T.P - Y3S2-WE-2.1
 * 
 */

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import com.sa.safetynet.accesscontroller.autodoorsystem.EmergencyDoorHelper;
import com.sa.safetynet.alert.SetAlertInterface;
import com.sa.safetynet.power.EmergencyHelper;
import com.sa.safetynet.weather.EmergencyWeatherHelper;

public class EmergencyServiceActivator implements BundleActivator {

	ServiceRegistration<?> sensorServiceRegister;
	ServiceRegistration<?> moduleAlertServiceRegister;
	//Getting services from other systems
	ServiceReference emergServiceReference;
	ServiceReference emergWeatherReference;
	ServiceReference setEmergAlertReference;
	ServiceReference emergDoorReference;
	
	public static EmergencyWeatherHelper emergWeather;
	public static EmergencyHelper emergPower;
	public static SetAlertInterface setAlert;
	public static EmergencyDoorHelper overrideDoor;
	
	public void start(BundleContext context) throws Exception {
		
		System.out.println("Emergency System is now online!");
		
		//Instantiating two types of EmergecnyService class for different type of subscribers
		EmergServiceInterface emrgService = new EmergencyService();
		
		
		//Registering Sensor services and Emergency check services to the OSGI service registry
		sensorServiceRegister = context.registerService(EmergServiceInterface.class.getName(), emrgService, null);
		
		
		//Getting emergency services available to Emergency service module from PowerServicePublisher module by accessing OSGI service registry
		emergServiceReference = context.getServiceReference(EmergencyHelper.class.getName());
		EmergencyHelper emergencyPowerServices = (EmergencyHelper)context.getService(emergServiceReference);
		
		//Getting emergency services available to Emergency service module from WeatherServicePublisher module by accessing OSGI service registry
		emergWeatherReference = context.getServiceReference(EmergencyWeatherHelper.class.getName());
		EmergencyWeatherHelper emergencyWeatherService = (EmergencyWeatherHelper) context.getService(emergWeatherReference);
		
		//Getting emergency services available to Emergency service module from AlertEmergencyPublisher module by accessing OSGI service registry
		setEmergAlertReference = context.getServiceReference(SetAlertInterface.class.getName());
		SetAlertInterface setAlertService = (SetAlertInterface) context.getService(setEmergAlertReference);
		
		//Getting emergency services available to Emergency service module from AutomatedDoorSystemPublisher module by accessing OSGI service registry
		emergDoorReference = context.getServiceReference(EmergencyDoorHelper.class.getName());
		EmergencyDoorHelper emergDoorService = (EmergencyDoorHelper) context.getService(emergDoorReference);
		
		// Assigning the received service instance to a static variable for direct access without the need for instantiating an object of this class.
		emergPower = emergencyPowerServices;
		emergWeather = emergencyWeatherService;
		setAlert = setAlertService;
		overrideDoor = emergDoorService;
		
	}

	
	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Emergency System is now offline!");
		sensorServiceRegister.unregister();
		moduleAlertServiceRegister.unregister();
		bundleContext.ungetService(emergDoorReference);
		bundleContext.ungetService(emergServiceReference);
		bundleContext.ungetService(emergWeatherReference);
		bundleContext.ungetService(setEmergAlertReference);
	}

}
