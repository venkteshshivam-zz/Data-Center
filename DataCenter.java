package com.ucc.cs6406new;

import java.util.concurrent.TimeUnit;

public class DataCenter {
	String Query = "duck";
	//String Query = "sample query";
	int shedulerResult;
	ThermalMgntImpl th = null;
	queryPreProcessImpl qp = new queryPreProcessImpl();
	SchedulingProcessImpl sp = new SchedulingProcessImpl();
	SensorImpl s = new SensorImpl();

	public void run() {

		System.out.println("Query Entered -----> " + Query);
		String[] cacheResult = qp.checkCache(Query);
		double at;
		if (cacheResult != null) {
			th = new ThermalMgntImpl();
			th.setTurn_on(true);
			th.autoThermalMgnt();
			at= th.avg_servertemp(s.serverTempSensor());
			shedulerResult = sp.machineScore(cacheResult);
			if(shedulerResult != 0)
			{
				String[] result = sp.processJob(shedulerResult);
	
				if (result[0] != null) {
					for (int i = 0; i < result.length; i++) {
						System.out.println(result[i]);
					
					}
				}
				
			}
			if(at<26)
			{
				CostCalc(1);
			}
			else
			{
				CostCalc(2);
			}
			
		}
			else {
				System.out.println("Found in Cache");
				CostCalc(0);
			}


	}

	public void CostCalc(int state) {
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(state == 0)
		{
			double powCon=500.0;
			int numOfJobs = 100;
			double totalConsumption = powCon*numOfJobs;
			
			//System.out.println("Total power consumption is: "+totalConsumption);
		}
		else if(state == 1)
		{
			double powCon=500.0 + 5.0;
			int numOfJobs = 100;
			double totalConsumption = powCon*numOfJobs;
			
			//System.out.println("Total power consumption is: "+totalConsumption);
		}
		else if(state == 2)
		{
			double powCon=200.0 + 500.0 + 5.0;
			int numOfJobs = 100;
			double totalConsumption = powCon*numOfJobs;
			
			//System.out.println("Total power consumption is: "+totalConsumption);
		}
		else
		{
			
		}
		
		
	}
	
	public static void main(String ar[]) {
		PowerManagerImpl pm = PowerManagerImpl.getInstance();
		pm.start();
		DataCenter dc = new DataCenter();
		dc.run();


	}

}
