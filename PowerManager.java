package com.ucc.cs6406new;

public interface PowerManager {
	
	// Manage the power supply system 
	public boolean managePowerCooling(boolean coolingState2);
	public boolean managePowerServer(double serverRoomVoltage2, int serverID);
	//public double getOutputVoltage();
	public void run();
	//public void adjustVoltage();
	public void monitorVoltage();
	public boolean reset();
    public void startPG();
    public void sendAlarm();
}
