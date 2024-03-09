package emergencyservice;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class EmergencyServiceActivator implements BundleActivator {

	ServiceRegistration<?> serviceRegister;

	
	public void start(BundleContext context) throws Exception {
		
		System.out.println("Emergency System is now online!");
		
		EmergServiceInterface emrgService = new EmergencyService();
		serviceRegister = context.registerService(EmergServiceInterface.class.getName(), emrgService, null);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Emergency System is now offline!");
		serviceRegister.unregister();
	}

}
