package com.ucc.cs6406new;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.Random;



class SchedulingTest {
	
	ServerImpl si = new ServerImpl();
	SchedulingProcessImpl sp = new SchedulingProcessImpl();
	queryPreProcessImpl qp = new queryPreProcessImpl();
	DataCenter dc = new DataCenter();
	@Test
	void testMachineScore() {
		
		Random rand = new Random();

		int  n = rand.nextInt(50) + 1;
		 
		System.out.println(n);
		
		int cpu = negTest(si.cpuArray());
		// the value of cpu and mem and mxc should be 0 if random number then failed
		assertTrue("one of the cpu id is negative hence false", cpu==0);
		int mem = negTest(si.memArray());
		assertTrue("one of the memory id is negative hence false", mem==0);
		int mxc = negTest(sp.machineResult);
		assertTrue("one of the machine result is negative hence false", mxc==0);
		
	}

	@Test
	void testSchedProcess() {
		
		assertTrue("Not enough power hence false",sp.schedProcess(qp.checkCache(dc.Query), sp.machineResult));
		//assertFalse("Power is available",sp.schedProcess(qp.checkCache(dc.Query), sp.machineResult));

	}

	@Test
	void testProcessJob() {
		
		Random rand = new Random();

		int  n1 = rand.nextInt(6000) -5999;
		 
		System.out.println(n1);
		
		int n = sp.processJob(sp.machineScore(qp.checkCache(dc.Query))).length;
		assertTrue("Results exceed permissible limit or no results recieved", n<=10 && n!=0 );
		
	}
	
	public int negTest(int arr[]) {
		int negative = 0;
        for (int i = 0; i < arr.length; i++){
            if(arr[i] < 0){
                negative = negative + 1;
            }
         }
        return negative;
	}
}
