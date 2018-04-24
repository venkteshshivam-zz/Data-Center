package com.ucc.cs6406new;

import java.sql.Blob;
import java.sql.SQLException;
public interface Sensor {

	public double acTempSensor();
	
	//Reads Air Conditioner temperatures  
	
	public double outTempSensor();
	
	//Reads AC room temperature
	
	public double[] serverTempSensor();
	
	//Reads Server room temperatures
	
	public Blob bioSensor() throws SQLException;
	
	//Reads the Bio-metric scan
	
	public String magReader();
	
	public double MPS_monitor_voltage();
	
	public double SPS_monitor_voltage();
	//Reads the string from the magnetic strip of the card
	
	
}
