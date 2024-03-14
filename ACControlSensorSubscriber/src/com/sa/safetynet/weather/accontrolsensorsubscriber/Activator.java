//IT21189944
//Madusanka G.K.I
//Group 2.1
package com.sa.safetynet.weather.accontrolsensorsubscriber;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import com.sa.safetynet.weather.WeatherService;

public class Activator implements BundleActivator {
	private ServiceReference weatherServiceRef;
	ServiceRegistration sensorService;
	
   
    private String[] area = {"area 1 ", "area 2 ", "area 3 ", "area 4 "};

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("AC Controller Starting!");
        ACControlSensorService acControl = new ACControlSensorServiceImpl();
        weatherServiceRef = context.getServiceReference(WeatherService.class.getName());
        if (weatherServiceRef != null) {
            WeatherService weatherService = (WeatherService) context.getService(weatherServiceRef);

            if (weatherService != null) {
                float temperature = weatherService.getTemprature();

                int tempInt = Math.round(temperature);

                if (tempInt > 31) {
                    System.out.println("Temperature is extremely high (" + temperature + "), setting AC to 16°C!");
                    acControl.turnOnAC(area, 16);
                } else if (tempInt >= 26 && tempInt <= 30) {
                    System.out.println("Temperature is high (" + temperature + "), setting AC to 18°C!");
                    acControl.turnOnAC(area, 18);
                } else if (tempInt >= 21 && tempInt <= 25) {
                    System.out.println("Temperature is warm (" + temperature + "), setting AC to 20°C!");
                    acControl.turnOnAC(area, 20);
                } else {
                    if (tempInt < 20) {
                        System.out.println("Temperature is low (" + temperature + "), turning AC off.");
                        acControl.turnOffAC(area);
                    } else {
                        System.out.println("Temperature is acceptable (" + temperature + "), no action needed.");
                    }
                }

                
                

            } else {
                System.out.println("Weather service unavailable!");
            }
        } else {
            System.out.println("Weather service not found!");
        }
        sensorService = context.registerService(ACControlSensorService.class.getName(), acControl, null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("AC Controller Stopping!");
        if (weatherServiceRef != null) {
            context.ungetService(weatherServiceRef);
        }
    }

}
