package com.sa.safetynet.accesscontroller.empdetection;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class EmployeeDetectionActivator implements BundleActivator {
	
	ServiceRegistration empDetectionServiceRegistration;

	public void start(BundleContext context) throws Exception {
		System.out.println("Initiating Employee Detection System...");
		
		DetectionService detectionService = new DetectionServiceImpl();
		empDetectionServiceRegistration = context.registerService(DetectionService.class.getName(), detectionService, null);
		
		System.out.println("Employee Detection System Initiated!!!");
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Aborting Employee Detection System...");
		empDetectionServiceRegistration.unregister();
		System.out.println("Employee Detection System Aborted!!!");
	}

}
