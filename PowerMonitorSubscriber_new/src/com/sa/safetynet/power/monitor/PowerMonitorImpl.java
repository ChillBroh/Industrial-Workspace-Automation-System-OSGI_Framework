package com.sa.safetynet.power.monitor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import com.sa.safetynet.power.alert.PowerAlert;
import com.sa.safetynet.power.alert.PowerAlertImpl;
import com.sa.safetynet.power.solar.SolarService;
import com.sa.safetynet.power.solar.SolarServiceImpl;
//import com.sa.safetynet.weather.accontrolsensorsubscriber.ACControlSensorService;
//import com.sa.safetynet.weather.accontrolsensorsubscriber.ACControlSensorServiceImpl;
import com.sa.safetynet.weather.heatcontrolsensorsubscriber.HeatControlSensorService;
import com.sa.safetynet.weather.heatcontrolsensorsubscriber.HeatControlSensorServiceImpl;
import com.sa.safetynet.weather.lightcontrolsensorsubscriber.LightControlSensorService;
import com.sa.safetynet.weather.lightcontrolsensorsubscriber.LightControlSensorServiceImpl;

public class PowerMonitorImpl implements PowerMonitor{
	private float powerConsumption = 0;
	
	private float solarPower = 0;
	
	HeatControlSensorService heatControlSensorService = new HeatControlSensorServiceImpl();
	
	LightControlSensorService lightControlSensorService = new LightControlSensorServiceImpl();
	
	PowerAlert powerAlert = new PowerAlertImpl();
	
	SolarService solarService = new SolarServiceImpl();
	
	Scanner sc = new Scanner(System.in);

	@Override
	public float calculatePower(HeatControlSensorService heatControlSensorService, LightControlSensorService lightControlSensorService, SolarService solarService) {
		heatControlSensorService = new HeatControlSensorServiceImpl();
		int heaters[] = heatControlSensorService.getTotalHeatersOn();
		int lights = lightControlSensorService.getTotalLightsOn();
		int total = (heaters[0]  + lights) * 50;
		return total;
	}

	@Override
	public void generateReport(HeatControlSensorService heatControlSensorService, LightControlSensorService lightControlSensorService, SolarService solarService) {
		powerConsumption = calculatePower(heatControlSensorService, lightControlSensorService, solarService);
		
		System.out.println(powerConsumption);
		float percentage = solarService.getContributionPercent();
		
		solarPower = powerConsumption * 0.5F;
		
		String directoryPath = "C:\\Users\\ishar\\Desktop";
        String fileName = "power-consumption-report.txt";
        
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        
        File file = new File(directory, fileName);
        
        try {
            FileWriter writer = new FileWriter(file);
            writer.write("Date : " + LocalDate.now() + "\n");
            writer.write("Total Power Consumption : " + powerConsumption + "\n");
            solarService.deActivateSolar("Off");
            writer.write("Solar Contribution Percetage : " + "50%" + "\n");
            writer.write("Generated solar power : " + solarPower + "\n");
            writer.write("Power consumption through the grid : " + (powerConsumption - solarPower) + "\n");
            writer.close();
            powerAlert.displayAlert("Find the power consumption report at : " + directoryPath + "\\" + fileName);
        }catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error occurred while writing to the file.");
        }
        
	}
}