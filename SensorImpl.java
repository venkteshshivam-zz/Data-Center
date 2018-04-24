package com.ucc.cs6406new;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Random;

public class SensorImpl implements Sensor {
	
	private double[] serTemp= {26.5, 26.5, 26.1, 26.0, 26.0};
	
	


	public double[] getSerTemp() {
		return serTemp;
	}

	private double acTemp = 25.0, outTemp = 30.0;
	
	

	

	public double getAcTemp_test() {
		return acTemp;
	}

	public void setAcTemp_test(double acTemp_test) {
		this.acTemp = acTemp_test;
	}

	public double getOutTemp_test() {
		return outTemp;
	}

	public void setOutTemp_test(double outTemp_test) {
		this.outTemp = outTemp_test;
	}
	
	

	@Override
	public double acTempSensor() {
		
		double acTemp=25.9;
		return acTemp;
	}
	
	//Reads Air Conditioner temperatures  
	
	@Override
	public double outTempSensor() {
		
		double outTemp=31.0;
		return outTemp;
		
	}
	
	
	//Reads Server room temperatures
	
	@Override
	public Blob bioSensor() throws SQLException {
		
		Connection conn = DriverManager.getConnection("<connnection string goes here>");
		Blob b = conn.createBlob();
		return b;
	}
	
	//Reads the Bio-metric scan
	
	@Override
	public String magReader() {
		
		String strip = "B1234567890123445^ACCESSCODE/NAME/ID";
		return strip;
		
	}

	public void setSerTemp(double[] serTemp2) {
		// TODO Auto-generated method stub
		this.serTemp = serTemp2;
	}

	@Override
	public double[] serverTempSensor() {
		// TODO Auto-generated method stub
		double[] serTemp= {26.5, 26.5, 26.1, 26.0, 26.0};
		return serTemp;
	}
	
	public double MPS_monitor_voltage() {
		
		Random rn = new Random();
		
		int MPSrange = 2000 - 500 +1;
		double MPS_vol = rn.nextInt(MPSrange) + 500;
		//System.out.println("MPS - "+MPS_vol);
		
		double MPS=MPS_vol;
		return MPS;
	}
	public double SPS_monitor_voltage() {
		
		Random rn = new Random();
		
		int SPSrange = 2000 - 500 +1;
		double SPS_vol = rn.nextInt(SPSrange) + 500;
		//System.out.println("SPS - "+SPS_vol);
		
		double SPS=SPS_vol;
		return SPS;
	}
	
	/*@Override
	public double PS_monitor_voltage() {
	    return PowerManagerImpl.getInstance().getOutputVoltage();
	}
	*/
    //Reads Power Supply output voltage
}
