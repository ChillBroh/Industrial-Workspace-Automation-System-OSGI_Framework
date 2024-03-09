package gasdetectionsensor;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import emergencyservice.EmergServiceInterface;

public class SensorActivator implements BundleActivator {

	ServiceReference serviceReference;
	
public void start(BundleContext context) throws Exception {
		
		serviceReference = context.getServiceReference(EmergServiceInterface.class.getName());
		
		EmergServiceInterface emergencyServices = (EmergServiceInterface)context.getService(serviceReference);
		
		//Run test class to simulate an emergency situation
		RunTest(emergencyServices);
	}


public void RunTest(EmergServiceInterface emergencyServices) {
		
		
		//Variables
				String emrgType = "gas";
				String userInput;
				String location;
				Boolean runAgain = false;
				
				//Scanner object
				Scanner sc = new Scanner(System.in);
				
				System.out.println("Gas detection sensor is now online...!! \n Running Test cases now..." );
				
				//Get user inputs- loop if user enters an invalid character
				System.out.println("Initiate test run(Y/N) : " );
				
				userInput = sc.next();
				
				System.out.println("Gas leak location : " );
				
				location = sc.next();
				
				
				//Get user inputs- loop if user enters an invalid character
				do{
					if(userInput.equals("Y") || userInput.equals("y")) {
						
						//Using services provided by the Emergency Services through OSGI framework 
						emergencyServices.SendNotification(emrgType);
						emergencyServices.ActivateEmergencyProtocol(location);
						runAgain = false;
					}else if(userInput.equals("N") || userInput.equals("n")) {
						runAgain = false;
						System.out.println("Test run declined!");
					}
					else {
						
						runAgain = true;
						System.out.println("Unrecognizable character. Please Enter again");
						System.out.println("Initiate test run(Y/N) : " );
						
						userInput = sc.next();
					}
				}while(runAgain == true);
				
				
				//Close the sc Object to avoid the resource leaks
				sc.close();
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Gas detection sensor is now offline...!!" );
		context.ungetService(serviceReference);
	}
}
