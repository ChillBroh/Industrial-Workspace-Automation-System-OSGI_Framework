//IT21189944
//Madusanka G.K.I
//Group 2.1
package clientservice;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.sa.safetynet.emergency.service.EmergServiceInterface;
import com.sa.safetynet.emergency.service.EmergencyServiceActivator;
import com.sa.safetynet.power.PowerServiceActivator;
import com.sa.safetynet.weather.WeatherService;


public class ClientActivator implements BundleActivator {

    private PowerServiceActivator powerServiceActivator = new PowerServiceActivator();
    private EmergencyServiceActivator emergencyServiceActivator = new EmergencyServiceActivator();
    private EmergServiceInterface emergencyServiceInterface;
    private WeatherService weatherServiceInterface;

    private Scanner sc = new Scanner(System.in);

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Client Starting...");

        // Start power service
        powerServiceActivator.start(context);
        // Start emergency service
        emergencyServiceActivator.start(context);

        // Retrieve emergency service reference
        ServiceReference<EmergServiceInterface> emergencyServiceReference =context.getServiceReference(EmergServiceInterface.class);

        // If emergency service reference is not null, get the service instance
        if (emergencyServiceReference != null) {
            emergencyServiceInterface = context.getService(emergencyServiceReference);

            // Check for emergency situations
            boolean isEmergency = checkEmergencySituation();

            if (isEmergency) {
                handleEmergency();
            } 
            System.out.println("No emergency situations detected.");
            this.displayService(context);
            
        } else {
            System.out.println("Emergency service not available.");
        }
        
        // Retrieve weather service reference
        ServiceReference<WeatherService> weatherServiceReference = context.getServiceReference(WeatherService.class);
        

        // If weather service reference is not null, get the service instance
        if (weatherServiceReference != null) {
            weatherServiceInterface = context.getService(weatherServiceReference);
            
        } else {
            System.out.println("Weather service is offline.");
        }
    }
 
    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Client Shutting Down...");
        // Stop emergency service
        emergencyServiceActivator.stop(context);;
        powerServiceActivator.stop(context);;
    }

    private boolean checkEmergencySituation() {
    	 String input;
    	 do {
    		 System.out.println("Is there any emergency situation? (yes/no)");
    	        input = sc.next();
         } while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));

       
        return input.equalsIgnoreCase("yes");
    }

    private void handleEmergency() {
        System.out.println("Emergency detected! System initialization aborted.");

        String type;
        do {
            System.out.println("Enter Emergency Type: (fire/gas) press -1 to exit");
            type = sc.next();
        } while (!type.equalsIgnoreCase("fire") && !type.equalsIgnoreCase("gas") && !type.equals("-1"));

        if (!type.equals("-1")) {
            System.out.print("Enter the Emergency Location: ");
            String location = sc.next();
            emergencyServiceInterface.sendNotification(type.toLowerCase());
            emergencyServiceInterface.activateEmergencyProtocol(location);
        }
    }
    
    private void displayService(BundleContext context) throws Exception {
    	
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
