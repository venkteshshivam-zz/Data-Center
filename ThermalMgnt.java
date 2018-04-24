package com.ucc.cs6406new;

public interface ThermalMgnt {
	
	/**
	 * Decide whether turn on the power supply
	 * @param server_temp
	 * @return
	 */
//	public boolean power_supply(double[] server_temp);

//	public boolean control_AirConditioner(double[] server_temp);
//	
//	public boolean control_NaturalCooling(double[] server_temp, double outside_temp);
//	
//	public boolean control_TruboDissipation(double[] server_temp, double outside_temp, double ac_temp);
//	
//	public boolean control_IceColdWater(double[] server_temp, double outside_temp, int t);
	
	public boolean autoThermalMgnt();

}