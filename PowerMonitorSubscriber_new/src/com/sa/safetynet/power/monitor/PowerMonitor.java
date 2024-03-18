package com.sa.safetynet.power.monitor;

import com.sa.safetynet.power.solar.SolarService;
import com.sa.safetynet.weather.heatcontrolsensorsubscriber.HeatControlSensorService;
import com.sa.safetynet.weather.lightcontrolsensorsubscriber.LightControlSensorService;

public interface PowerMonitor {
	public float calculatePower(HeatControlSensorService heatControlSensorService, LightControlSensorService lightControlSensorService, SolarService solarService);
	
	public void generateReport(HeatControlSensorService heatControlSensorService, LightControlSensorService lightControlSensorService, SolarService solarService);
}