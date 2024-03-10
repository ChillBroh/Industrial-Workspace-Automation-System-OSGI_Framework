package emergencyservice;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

public class EmergencyServiceActivator implements BundleActivator {

	ServiceRegistration<?> sensorServiceRegister;
	ServiceRegistration<?> moduleAlertServiceRegister;
	

	
	public void start(BundleContext context) throws Exception {
		
		System.out.println("Emergency System is now online!");
		
		EmergServiceInterface emrgService = new EmergencyService();
		EmergencyCheckInterface emrgNotify = new EmergencyService();
		
		sensorServiceRegister = context.registerService(EmergServiceInterface.class.getName(), emrgService, null);
		moduleAlertServiceRegister = context.registerService(EmergencyCheckInterface.class.getName() , emrgNotify, null);
	}

	
	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Emergency System is now offline!");
		sensorServiceRegister.unregister();
		moduleAlertServiceRegister.unregister();
	}

}
