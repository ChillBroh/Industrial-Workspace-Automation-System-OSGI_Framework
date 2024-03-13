//IT21189944
//Madusanka G.K.I
//Group 2.1
package com.sa.safetynet.weather.heatcontrolsensorsubscriber;

import com.sa.safetynet.weather.WeatherService;


import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	private ServiceReference weatherServiceRef;
	ServiceRegistration sensorService;
   
    private String[] area = {"hall 1", "hall 2", "hall 3", "hall 4"};

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Heater Controller Starting!");
        HeatControlSensorService heaterControl = new HeatControlSensorServiceImpl(); 
        weatherServiceRef = context.getServiceReference(WeatherService.class.getName());
        if (weatherServiceRef != null) {
            WeatherService weatherService = (WeatherService) context.getService(weatherServiceRef);

            if (weatherService != null) {
                float temperature = weatherService.getTemprature();
                int tempInt = Math.round(temperature); 
                if (temperature < 20) { 
                    System.out.println("Temperature is low (" + temperature + "), turning on heater to 24°C!");
                    heaterControl.turnOnHeater(area, tempInt);
                } else {
                    System.out.println("Temperature is normal (" + temperature + "), turning heater off.");
                    heaterControl.turnOffHeater(area);
                }
                
               
            } else {
                System.out.println("Weather service unavailable!");
            }
        } else {
            System.out.println("Weather service not found!");
        }
        sensorService = context.registerService(HeatControlSensorService.class.getName(), heaterControl, null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
    	context.ungetService(weatherServiceRef);
        System.out.println("Heater Controller Stopping!");
    }


}
