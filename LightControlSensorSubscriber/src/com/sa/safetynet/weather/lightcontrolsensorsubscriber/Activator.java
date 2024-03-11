//IT21189944
//Madusanka G.K.I
//Group 2.1
package com.sa.safetynet.weather.lightcontrolsensorsubscriber;

import com.sa.safetynet.weather.WeatherService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {

    private ServiceReference weatherServiceRef;
    private LightControlSensorService lightController = new LightControlSensorServiceImpl(); // Create LightControl instance
    private String[] area = {"Area 1", "Area 2", "Area 3", "Area 4"};

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Light Controller Starting!");

        weatherServiceRef = context.getServiceReference(WeatherService.class.getName());
        if (weatherServiceRef != null) {
            WeatherService weatherService = (WeatherService) context.getService(weatherServiceRef);

            if (weatherService != null) {
                float lightIntensity = weatherService.getLightIntensity();

                if (lightIntensity > 40) {
                    System.out.println("Light intensity is high (" + lightIntensity + "), turning off lights!");
                    lightController.turnOffLights(area);
                } else {
                    System.out.println("Light intensity is low (" + lightIntensity + "), turning lights on!");
                    lightController.turnOnLights(area);
                }
            } else {
                System.out.println("Weather service unavailable!");
            }
        } else {
            System.out.println("Weather service not found!");
        }
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Light Controller Stopping!");
        if (weatherServiceRef != null) {
            context.ungetService(weatherServiceRef);
        }
    }
}
