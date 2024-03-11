//IT21189944
//Madusanka G.K.I
//Group 2.1

package com.sa.safetynet.weather;

import com.sa.safetynet.alert.GetAlertInterface;
import com.sa.safetynet.power.PowerService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	private ServiceReference powerServiceRef;
    private ServiceReference  emergServiceRef;
    private ServiceRegistration serviceRegistration;
    private ServiceRegistration emergWeatherServiecReg;

    public void start(BundleContext context) throws Exception {
        WeatherService weatherService = new WeatherServiceImpl();
        EmergencyWeatherHelper  emergWeatherService = new WeatherServiceImpl();
        
        System.out.println("Weather Service Starting!");
        powerServiceRef = context.getServiceReference(PowerService.class.getName());
        
        if (powerServiceRef != null) {
            PowerService powerService = (PowerService) context.getService(powerServiceRef);
            boolean isPowerOn = powerService.powerOnStatus("on");
            System.out.println("Power Status: " + (isPowerOn ? "On" : "Off"));
            
            if (isPowerOn) {
                emergServiceRef = context.getServiceReference(GetAlertInterface.class.getName());
                if (emergServiceRef != null) {
                	GetAlertInterface emergencyService = (GetAlertInterface) context.getService(emergServiceRef);
                    boolean isEmergency = emergencyService.getEmergState();
                    if (isEmergency) {
                        System.out.println("Emergency Mode On! Shutting down Weather Service.");
                        stop(context); 
                    } else {
                        float temperature = getUserTemperatureInput();
                        float lightIntensity = getUserLightIntensityInput();

                        weatherService.setTemperature(temperature);
                        weatherService.setLightIntensity(lightIntensity);

                        serviceRegistration = context.registerService(WeatherService.class.getName(), weatherService, null);
                        emergWeatherServiecReg = context.registerService(EmergencyWeatherHelper.class.getName(), emergWeatherService, null);
                    }
                } else {
                    System.out.println("Emergency service not available!");
                    float temperature = getUserTemperatureInput();
                    float lightIntensity = getUserLightIntensityInput();

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
        System.out.println("Weather Service Shutting Down!");
        if (serviceRegistration != null) {
            serviceRegistration.unregister();
        }
    }
   
    private float getUserTemperatureInput() {
        return 20.0f; 
    }

    private float getUserLightIntensityInput() {
        return 0.5f; 
    }

}
