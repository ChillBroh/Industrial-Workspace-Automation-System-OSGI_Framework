package com.sa.safetynet.power;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

//import com.sa.safetynet.emergency.service.EmergServiceInterface;

//import weatherpublisher.WeatherService;

public class PowerServiceActivator implements BundleActivator {
	ServiceRegistration powerServiceRegistration;
	ServiceRegistration emergServiceRegistration;

	
	
	public void start(BundleContext context) throws Exception {
		
		System.out.println("Power Service is starting...");
		
		PowerService powerService = new PowerServiceImpl();
		EmergencyHelper emrgPowerService = new PowerServiceImpl();
		
		powerServiceRegistration = context.registerService(PowerService.class.getName(), powerService, null);
		emergServiceRegistration = context.registerService(EmergencyHelper.class.getName(), emrgPowerService, null);
		
		powerService.powerOnStatus("On");
		System.out.println("Power Service Started!!!");
		this.displayService();
	}


	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Power Service is aborting...");
		powerServiceRegistration.unregister();
		emergServiceRegistration.unregister();
		
	}
	
    private void displayService(){
    	
      	System.out.println("---------------------------------------------------------------------");
    	System.out.println();
    	System.out.println("     _______  ____       _______   _______   ________   ___    ___    _ _ _  __  ________   ________ tm");
    	System.out.println("    /______/ / /\\ \\     |   ____| |  _____| |__   ___|  \\  \\  /  /   |     \\ | | |  _____| |__   ___|");
    	System.out.println("   /_/___   / /  \\ \\    |  |____  | |_____     |  |      \\  \\/  /    |  |\\  \\| | | |____      |  |");
    	System.out.println("  /______/ / /____\\ \\   |   ____| |  _____|    |  |       \\    /     |  | \\    | | _____|     |  |");
    	System.out.println(" _____/ / / /------\\ \\  |  |      | |_____     |  |        |  |      |  |  \\   | | |_____     |  |");
    	System.out.println("/______/ /_/        \\_\\ |__|      |_______|    |__|        |__|      |__|   \\__| |_______|    |__|");
    	System.out.println("");
    	System.out.println("              -------------------------------------------");
    	System.out.println("              // Welcome To SafetyNet Service Console //");
    	System.out.println("              -------------------------------------------");
    	System.out.println();
    	
    	
	}

}

