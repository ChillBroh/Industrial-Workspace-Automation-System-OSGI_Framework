//IT21189944
//Madusanka G.K.I
//Group 2.1
package com.sa.safetynet.weather.accontrolsensorsubscriber;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.sa.safetynet.weather.WeatherService;

public class Activator implements BundleActivator {
	private ServiceReference weatherServiceRef;
    private final ACControlSensorService acControl = new ACControlSensorServiceImpl();
    private String[] area = {"hall 1", "hall 2", "hall 3", "hall 4"};

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("AC Controller Starting!");

        weatherServiceRef = context.getServiceReference(WeatherService.class.getName());
        if (weatherServiceRef != null) {
            WeatherService weatherService = (WeatherService) context.getService(weatherServiceRef);

            if (weatherService != null) {
                float temperature = weatherService.getTemprature();

                int tempInt = Math.round(temperature);

                switch (tempInt) {
                    case 31:
                    case Integer.MAX_VALUE:
                        System.out.println("Temperature is extremely high (" + temperature + "), setting AC to 16°C!");
                        acControl.turnOnAC(area, 16);
                        break;
                    case 26:
                    case 30:
                        System.out.println("Temperature is high (" + temperature + "), setting AC to 18°C!");
                        acControl.turnOnAC(area, 18);
                        break;
                    case 21:
                    case 25:
                        System.out.println("Temperature is warm (" + temperature + "), setting AC to 20°C!");
                        acControl.turnOnAC(area, 20);
                        break;
                    default:
                        System.out.println("Temperature is acceptable (" + temperature + "), turning AC off.");
                        acControl.turnOffAC(area);
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
        System.out.println("AC Controller Stopping!");
        if (weatherServiceRef != null) {
            context.ungetService(weatherServiceRef);
        }
    }

}
