package com.sa.safetynet.accesscontroller.facedetection;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.sa.safetynet.accesscontroller.autodoorsystem.AutomatedDoorService;
import com.sa.safetynet.accesscontroller.empdetection.DetectionService;
import com.sa.safetynet.accesscontroller.notificationsystem.NotificationSystem;
import com.sa.safetynet.power.PowerService;

public class FaceDetectionActivator implements BundleActivator {

	ServiceReference PowerOnStatusReference;
	ServiceReference faceDetectionServiceReference;
	ServiceReference controllDoorServiceReference;
	ServiceReference notificationServiceReference;
	
	
	public static DetectionService checkEmpValidity;
	public static AutomatedDoorService controllAutoDoor;
	public static NotificationSystem notifyEmp;
	

	public void start(BundleContext context) throws Exception {
		
		PowerOnStatusReference = context.getServiceReference(PowerService.class.getName());
		PowerService powerService = (PowerService) context.getService(PowerOnStatusReference);
		
		boolean isPowerOn = powerService.powerOnStatus("on");
		FaceDetectionImpl FDA =  new FaceDetectionImpl();
		
		System.out.println("Power status: " + isPowerOn);
		
		if(isPowerOn) {
			System.out.println("Initiating Face Detection Service...");
			
			
			faceDetectionServiceReference = context.getServiceReference(DetectionService.class.getName());
			DetectionService empDetection = (DetectionService)context.getService(faceDetectionServiceReference);
			
			controllDoorServiceReference = context.getServiceReference(AutomatedDoorService.class.getName());
			AutomatedDoorService controllDoor = (AutomatedDoorService) context.getService(controllDoorServiceReference);
			
			notificationServiceReference = context.getServiceReference(NotificationSystem.class.getName());
			NotificationSystem notificationSys = (NotificationSystem) context.getService(notificationServiceReference);
			
		
			
			checkEmpValidity = empDetection;
			controllAutoDoor = controllDoor;
			notifyEmp = notificationSys;

			
			System.out.println("Face Detection Service Initiated!!!");
			FDA.sendDetails();
			
		}else {
            System.out.println("Power service not available!");
		}

	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Aborting Face Detection Service...");
		context.ungetService(faceDetectionServiceReference);
		context.ungetService(PowerOnStatusReference);
		context.ungetService(controllDoorServiceReference);
		context.ungetService(notificationServiceReference);
		System.out.println("Face Detection Service Aborted!!!");
	}

}
