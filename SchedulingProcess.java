package com.ucc.cs6406new;

public interface SchedulingProcess {
	public int machineScore(String[] opQuery);
	
	// calculate machine scores
	
	public boolean schedProcess(String[] opQuery, int[] machineResult);
	
	// schedule the query for processing
	
	public String[] processJob(int chosenProc);
	
	// process the job using the chosen processor
	
}
