package com.ucc.cs6406new;

import java.util.Random;

public class PowerManagerImpl extends Thread implements PowerManager {
	private final double CoolingStateVoltage = 1000;
	private final int PGStartTime = 2;
	private final int MainPS = 0;
	private final int MainPS_Request = 1;
	private final int SecondPS = 2; 
	private final int SecondPS_Request = 3; 
	private final int UPS = 4;
	private final int PG = 5;
	private int state = 0;
	public double L;
	public double outputVoltage = 0;
	public double monitoredVoltage_MPS = 500;
	public double monitoredVoltage_SPS = 500;
	private int t = 5;
	private boolean manualReset = false;
	public boolean coolingState=false;
	public double serverRoomVoltage=0;
	public double constantPowerConsumption = 500;
	public int serverID_PM=1;
	PowerManagerImpl() {}
	private static PowerManagerImpl instance = null;
	public static PowerManagerImpl getInstance() {
		if (instance == null) {
			instance = new PowerManagerImpl();
		}
		return instance;
	}
	
	public double getOutputVoltage() {
		return outputVoltage;
	}
	
	@Override
	public boolean managePowerCooling(boolean coolingState2) {
		coolingState = coolingState2;
		if(coolingState2 == true)
		{
			System.out.println("Power Management: Request for adjust power to cooling system has been received and processing!");
		}
		else
		{
			System.out.println("Power Management: Request to off cooling system has been received and processing!");
		}
		run();
		return outputVoltage == L; 
	}
	
	@Override
	public boolean managePowerServer(double serverRoomVoltage2, int serverID) {
		System.out.println("Power Management: Request for adjust power to server " + serverID + " has been received and processing!");
		serverID_PM = serverID;
		serverRoomVoltage = serverRoomVoltage2;
		run();
		return outputVoltage == L;
	}
	@Override
	public void run() {
		L = (coolingState ? CoolingStateVoltage : 0) + serverRoomVoltage + constantPowerConsumption;
		//System.out.println(L);
		monitorVoltage();
		
		switch(state) {
			case MainPS:
				mainPS_Exec();
				break;
			case MainPS_Request:
				mainPS_Request_Exec();
				break;
			case SecondPS:
				secondPS_Exec();
				break;
			case SecondPS_Request:
				secondPS_Request_Exec();
				break;
			case UPS:
				UPS_Exec();
				break;
			case PG:
				PG_Exec();
				break;
		}
	}
	
	public void mainPS_Exec() {
		
		if(monitoredVoltage_MPS < L) {
			
			//monitorVoltage();
			//t = 0;
			//state = MainPS_Request;
			mainPS_Request_Exec();
		}
		else {
			System.out.println("Energy from Primary Power Source");
			//monitorVoltage(); 
			}
	}
	
	public void mainPS_Request_Exec() {
		if(monitoredVoltage_MPS>L && t < 5) {
			//state = MainPS;
			mainPS_Exec();
		}
		else if (monitoredVoltage_SPS >= L && t >= 5) {
			//state = SecondPS;
			secondPS_Exec();
		}
		else if(monitoredVoltage_SPS < L && t >= 5)
		//else if(monitoredVoltage_SPS < L && t > 5)//mutation
		{
			startPG();
			sendAlarm();
			//t = 0;
			//state = UPS;
			UPS_Exec();
		}
		else { 
			//monitorVoltage();
			//t ++;
		}
	}


	public void secondPS_Exec() {
		if (reset()) {
			//state = MainPS;
			mainPS_Exec();
		} 
		else if(monitoredVoltage_SPS < L) {
			//t = 0;
			//state = SecondPS_Request;
			secondPS_Request_Exec();
		}
		else { 
			System.out.println("Energy from Secondary Power Source");
			//monitorVoltage();
		}
	}
	
	public void secondPS_Request_Exec() {
		
	if(monitoredVoltage_SPS > L && t < 5) {
	//if(monitoredVoltage_SPS < L && t < 5) {  //mutation
			//state = SecondPS;
			secondPS_Exec();
		}
		else if(monitoredVoltage_MPS>=L && t >= 5) {
			//state = MainPS;
			mainPS_Exec();
		}
		else if (monitoredVoltage_SPS < L && t >= 5) {
			startPG();
			sendAlarm();
			//t = 0;
			//state = UPS;
			UPS_Exec();
		}
		else {
			//t ++;
			//monitorVoltage();
		}
		
	}
	
	public void UPS_Exec() {
		
		if (reset()) {
			//state = MainPS;
			mainPS_Exec();
		} 
		else if(t > PGStartTime) {
			sendAlarm();
			System.out.println("UPS Mode");
			System.out.println("SWitching from UPS to power Generators");
			//state = PG;
			PG_Exec();
		}
		else { 
			System.out.println("UPS Mode");
//			System.out.println("====== Mutation Test 3: Changed time condition - SURVIVED");
		}
		

	}
	
	public void PG_Exec() {
		if (reset()) {
			state = MainPS;
		}
		else {
			System.out.println("Power Generator Mode");
//			System.out.println("====== Mutation Test 4: Changed reset condition - SURVIVED");
		}
	}

	
//	public void adjustVoltage() {
//		if (monitoredVoltage_MPS < L) {
//			outputVoltage = L;
//			System.out.println("Power Management: The current power voltage has been adjusted to: " + outputVoltage);
//		}
//	}
	
	public void monitorVoltage() {
		monitoredVoltage_MPS = new SensorImpl().MPS_monitor_voltage();
		monitoredVoltage_SPS = new SensorImpl().SPS_monitor_voltage();
		System.out.println("Power Management: The current power voltage of sensors are: " + monitoredVoltage_MPS +" : "+ monitoredVoltage_SPS);
	}
	
	public boolean reset() {
	 manualReset = false;
		return manualReset;
	}
	
	public void startPG() {
		//System.out.println("The Power Generator is starting");
	}
	
	public void sendAlarm() {
//		if (state == SecondPS_Request) {
//			System.out.println("Power Management: Main and Secondary Power supplies have failed, switching on UPS");
//		}
//		else {
//			System.out.println("Power Management: Switching from UPS to Power Generators");
//		}
	}
}
