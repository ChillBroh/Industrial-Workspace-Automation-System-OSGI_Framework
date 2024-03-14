//IT21189944
//Madusanka G.K.I
//Group 2.1

package com.sa.safetynet.weather;

import com.sa.safetynet.alert.GetAlertInterface;
import com.sa.safetynet.power.PowerService;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	private ServiceReference<?> powerServiceRef;
    private ServiceReference<?>  emergServiceRef;
    private ServiceRegistration<?> serviceRegistration;
    private ServiceRegistration<?> emergWeatherServiecReg;

    public void start(BundleContext context) throws Exception {
        WeatherService weatherService = new WeatherServiceImpl();
        EmergencyWeatherHelper  emergWeatherService = new WeatherServiceImpl();
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Weather Service Starting!");
        powerServiceRef = context.getServiceReference(PowerService.class.getName());
        
        if (powerServiceRef != null) {
            PowerService powerService = (PowerService) context.getService(powerServiceRef);
            boolean isPowerOn = powerService.powerOnStatus("on");
            System.out.println("Power Status: " + (isPowerOn ? "On" : "Off"));
            float temperature;
            float lightIntensity;
            if (isPowerOn) {
                emergServiceRef = context.getServiceReference(GetAlertInterface.class.getName());
                if (emergServiceRef != null) {
                	GetAlertInterface emergencyService = (GetAlertInterface) context.getService(emergServiceRef);
                    boolean isEmergency = emergencyService.getEmergState();
                    if (isEmergency) {
                        System.out.println("Emergency Mode On! Shutting down Weather Service.");
                        stop(context); 
                    } else {
                    	System.out.println("No Emergency Situation");
                    	System.out.println("+++++++++++++++++++++++++++");
                        System.out.println("Weather Service Starting!");
                     	System.out.println("+++++++++++++++++++++++++++");
                        System.out.println("Getiing Temperature : ");
                        temperature = sc.nextFloat();
                        System.out.println("Getiing Light Intensity : ");
                        lightIntensity = sc.nextFloat();


                        weatherService.setTemperature(temperature);
                        weatherService.setLightIntensity(lightIntensity);

                        weatherService.setTemperature(temperature);
                        weatherService.setLightIntensity(lightIntensity);

                        serviceRegistration = context.registerService(WeatherService.class.getName(), weatherService, null);
                        emergWeatherServiecReg = context.registerService(EmergencyWeatherHelper.class.getName(), emergWeatherService, null);
                    }
                } else {
                    System.out.println("Emergency service not available!");
                	System.out.println("No Emergency Situation");
                	System.out.println("+++++++++++++++++++++++++++");
                    System.out.println("Weather Service Starting!");
                 	System.out.println("+++++++++++++++++++++++++++");
                    System.out.println("Getiing Temperature : ");
                    temperature = sc.nextFloat();
                    System.out.println("Getiing Light Intensity : ");
                    lightIntensity = sc.nextFloat();

                    weatherService.setTemperature(temperature);
                    weatherService.setLightIntensity(lightIntensity);

                    weatherService.setTemperature(temperature);
                    weatherService.setLightIntensity(lightIntensity);
                    serviceRegistration = context.registerService(WeatherService.class.getName(), weatherService, null);
                }
            } else {
                System.out.println("Power unavailable! Stopping weather check.");
            }
        } else {
            System.out.println("Power service not available!");
        }
    }

    public void stop(BundleContext context) throws Exception {
       
        if (serviceRegistration != null) {
            serviceRegistration.unregister();
        }
        if (emergWeatherServiecReg != null) {
        	emergWeatherServiecReg.unregister();
        }
        
        context.ungetService(emergServiceRef);
        context.ungetService(powerServiceRef);
        
        System.out.println("Weather Service Shutting Down!");
    }
   


}
