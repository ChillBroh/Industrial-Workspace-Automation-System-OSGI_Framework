package com.sa.safetynet.alert;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;



public class AlertActivator implements BundleActivator {
	
	ServiceRegistration<?> getAlertServiceRegister;
	ServiceRegistration<?> setAlertServiceRegister;
	
	public void start(BundleContext context) throws Exception {
		
		GetAlertInterface getAlertObj = new AlertController();
		SetAlertInterface setAlertObj = new AlertController();
		
		//Registering Sensor services and Emergency check services to the OSGI service registry
		getAlertServiceRegister = context.registerService(GetAlertInterface.class.getName(), getAlertObj, null);
		setAlertServiceRegister = context.registerService(SetAlertInterface.class.getName() , setAlertObj, null);
				
	}

	public void stop(BundleContext bundleContext) throws Exception {
		
		getAlertServiceRegister.unregister();
		setAlertServiceRegister.unregister();
	}

}
