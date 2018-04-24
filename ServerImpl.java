package com.ucc.cs6406new;

public class ServerImpl extends SensorImpl implements Server {
	
	@Override
	public int[] cpuArray() {
		
		int cpu[] = {12,43,32,23,28};
		return cpu;
	}
	
	// send CPU scores
	
	@Override
	public int[] memArray() {
		
		int mem[] = {23,12,45,32,54};
		return mem;
	}
	
	// send memory scores

}
