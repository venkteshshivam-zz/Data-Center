package com.ucc.cs6406new;

import java.util.concurrent.TimeUnit;

public class ThermalMgntImpl extends Thread implements ThermalMgnt {

	private double[] server_temp;
	private double outside_temp;
	private double ac_temp;
	PowerManagerImpl pm = PowerManagerImpl.getInstance();
	SensorImpl sensor = new SensorImpl();
	DataCenter dc = new DataCenter();
	private int state = 0;
	public static final int OFF = 0;
	public static final int IDLE = 1;
	public static final int AC_Natural = 2;
	public static final int AC_Turbo = 3;
	public static final int AC_Water = 4;

	private boolean power_request = false;
	private int t = 0;
	private boolean heat_exchanger = false;

	private boolean ac_cooling = false;
	private boolean natural_cooling = false;
	private boolean turbo_cooling = false;
	private boolean water_cooling = false;
	private boolean turn_on = false;

	private double set_temp = 26.0;
	private double avg_servertemp;

	public ThermalMgntImpl() {
		super();
		this.server_temp = sensor.serverTempSensor();
		this.outside_temp = sensor.outTempSensor();
		this.ac_temp = sensor.acTempSensor();
	}

	public void update(SensorImpl sensor) {
		this.server_temp = sensor.getSerTemp();
		this.outside_temp = sensor.getOutTemp_test();
		this.ac_temp = sensor.getAcTemp_test();
		
	}

	


	public void run()
	{
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(true)
		{
			double[] array = sensor.serverTempSensor();
			String s="";
			for (int i = 0; i < array.length; i++) {
			    array[i]--;
			    s += array[i]-- + ",";
			}
			System.out.println("ThermalMgmtImpl : Sensor Readings -----> " + s);
			sensor.setSerTemp(array);
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(avg_servertemp(array) < 26 )
			{
				
				System.out.println("ThermalMgmtImpl -----> The temperature is below the threshold temperature");
				pm.managePowerCooling(false);
				break;
			}

		}

		

	}
	
	@Override
	public boolean autoThermalMgnt() {
		ThermalMgntImpl obj = new ThermalMgntImpl();
		
		this.avg_servertemp = avg_servertemp(server_temp);
		System.out.println("AAAAA outside_temp: " + this.outside_temp);
		
		for(int i = 0; i < 2; i ++) {
		switch (state) {
		case OFF:
			if (turn_on) {
				state = IDLE;
				System.out.println("ThermalMgmtImpl -----> now  I am going to IDLE ");
			}
			break;
			
		case IDLE:
			if (!turn_on) {
				state = OFF;
			} else if (avg_servertemp >= 26 && outside_temp <= 13) {
				power_request = true;
				Boolean req = true;
				System.out.println("ThermalMgmtImpl -----> now  I am in IDLE ");
				System.out.println("averageTemperture1: " +  avg_servertemp);
				System.out.println("ThermalMgmtImpl1 -----> Avg Temperature above the threshold value.Request PM for more power with natural cooling");
				pm.managePowerCooling(req);
				obj.start();
				state = AC_Natural;
			}   
			
			else if (avg_servertemp >= 26 && outside_temp > 13) {
				power_request = true;
				Boolean req = true;
				System.out.println("ThermalMgmtImpl -----> now  I am in IDLE ");
				System.out.println("averageTemperture2:" +  avg_servertemp);
				System.out.println("ThermalMgmtImpl2 -----> Avg Temperature above the threshold value.Request PM for more power with turbo cooling");
				pm.managePowerCooling(req);
				obj.start();
				state = AC_Turbo;
			} 
			
			else {

				state = IDLE;
			}
			//dc.CostCalc(2);
			break;
			
		case AC_Natural:
			ac_cooling = true;
			natural_cooling = true;
			turbo_cooling = false;
			water_cooling = false;
			System.out.println("ThermalMgmtImpl -----> now  I am in AC_Natural ");
			executeCooling();

			
			if (ac_temp >= 50 || outside_temp > 13) {
				t = 0;
				state = AC_Turbo;

			} else if (avg_servertemp < 26) {
				state = IDLE;

			} else {

			}
			break;

		case AC_Turbo:
			ac_cooling = true;
			natural_cooling = true;
			turbo_cooling = true;
			water_cooling = false;
			
			System.out.println("ThermalMgmtImpl -----> now  I am in AC_Turbo ");
			executeCooling();
			

			if (outside_temp <= 13 && ac_temp <= 50) {
				state = AC_Natural;
			
			} else if (avg_servertemp < 26) {
				power_request = false;
				heat_exchanger = false;
				state = IDLE;
			
			} else if (ac_temp >= 60 || t >= 10800) {
				t = 0;
				state = AC_Water;
			
			} else {
				t++;
				heat_exchanger = true;
			}
			break;
			
		case AC_Water:
			ac_cooling = true;
			natural_cooling = true;
			turbo_cooling = false;
			water_cooling = true;

			System.out.println("ThermalMgmtImpl -----> now  I am in AC_Water ");
			executeCooling();
			

			if (ac_temp < 50) {
				state = AC_Turbo;

			} else {

			}
			break;

		}
		}

		return false;
	}

	private void executeCooling() {
		switchOnAC(ac_cooling);
		switchOnNaturalHeatDisp(natural_cooling);
		switchOnTurboCooling(turbo_cooling);
		switchOnWaterCooling(water_cooling);
	}

	private void switchOnAC(boolean ac_cooling2) {
		if (ac_cooling2) {
			// turn on ac
			System.out.println("ThermalMgmtImpl -----> now  I switch on ac_cooling ");
		} else {

		}

	}

	private void switchOnNaturalHeatDisp(boolean natural_cooling2) {
		if (natural_cooling2) {
			// turn on natural heat disp
			System.out.println("ThermalMgmtImpl -----> now  I switch on natural_cooling ");
		} else {

		}
	}

	private void switchOnTurboCooling(boolean turbo_cooling2) {
		if (turbo_cooling2) {
			// turn on turbo cooling
			System.out.println("ThermalMgmtImpl -----> now  I switch on turbo_cooling ");
		} else {

		}

	}

	private void switchOnWaterCooling(boolean water_cooling2) {
		if (water_cooling2) {
			// turn on water cooling
			System.out.println("ThermalMgmtImpl -----> now  I switch on water_cooling ");
		} else {

		}

	}

	public static boolean isTempOver(double[] server_temp, double set_temp) {
		for (double temp : server_temp) {
			if (temp >= set_temp) {
				return true;
			}
		}
		return false;
	}

	public static double avg_servertemp(double[] server_temp) {
		double sum = 0;
		int count = 0;

		for (double temp : server_temp) {
			sum += temp;
			count++;
		}
		return sum / count;
	}

	public boolean isHeat_exchanger() {
		return heat_exchanger;
	}

	public void setHeat_exchanger(boolean heat_exchanger) {
		this.heat_exchanger = heat_exchanger;
	}

	public boolean isAc_cooling() {
		return ac_cooling;
	}

	public void setAc_cooling(boolean ac_cooling) {
		this.ac_cooling = ac_cooling;
	}

	public boolean isNatural_cooling() {
		return natural_cooling;
	}

	public void setNatural_cooling(boolean natural_cooling) {
		this.natural_cooling = natural_cooling;
	}

	public boolean isTurbo_cooling() {
		return turbo_cooling;
	}

	public void setTurbo_cooling(boolean turbo_cooling) {
		this.turbo_cooling = turbo_cooling;
	}

	public boolean isWater_cooling() {
		return water_cooling;
	}

	public void setWater_cooling(boolean water_cooling) {
		this.water_cooling = water_cooling;
	}

	public boolean isTurn_on() {
		return turn_on;
	}

	public void setTurn_on(boolean turn_on) {
		this.turn_on = turn_on;
	}

	public double[] getServer_temp() {
		return server_temp;
	}

	public void setServer_temp(double[] server_temp) {
		this.server_temp = server_temp;
	}


	public void setState(int state) {
		this.state = state;
	}
	
	
	

}
