package com.ucc.cs6406new;


public class SchedulingProcessImpl extends ServerImpl implements SchedulingProcess {
	PowerManagerImpl pm = PowerManagerImpl.getInstance();
	 int machineResult [] = {20,30,50,70,10};
	
	public int machineScore(String[] opQuery) {
		int [] cpu = null;
		int [] memory = null;
		
		// call cpu and memory arrays
		
		cpu = cpuArray();
		memory = memArray();
		 int timer = 0;
		 
		for(int i=0; i<=5; i++) {
				
			++timer;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		// calculate machine scores
		
		//machineResult = new int[] 	
		int processorToUse = 5;
		Boolean procAssign;
		
		
		procAssign = schedProcess(opQuery,machineResult);
		while (!procAssign) {
			
			procAssign = schedProcess(opQuery,machineResult);
		}
	
		return processorToUse;
		
		
	}

	@Override
	public boolean schedProcess(String[] opQuery, int[] machineResult) {
		Boolean chosenProc =true;
		boolean powerSupply=false;
		double chosenVol = 5.0;
		double[] tempArray = null;
		tempArray = serverTempSensor();
		int processorToUse = 5;
		// schedule the process		
		
		
		System.out.println("SheduleJob -----> The job has been scheduled and a request to power manager has been sent");
		powerSupply=pm.managePowerServer(chosenVol,processorToUse);
		if(powerSupply)
		{
			chosenProc = false;
			
		}
		return chosenProc;
	}

	@Override
	public String[] processJob(int chosenProc) {
		
		//Do the processing
		String [] queryResults = {"These are the sample processing results1","These are the sample processing results2","These are the sample processing results3",
				"These are the sample processing results4","These are the sample processing results5","These are the sample processing results6",
				"These are the sample processing results7"};
		
		System.out.println("ProcessJob -----> Query results posted");
		pm.managePowerServer(0.0,chosenProc);
		
		return queryResults;
		
	}

}
