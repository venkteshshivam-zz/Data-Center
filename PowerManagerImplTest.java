package com.ucc.cs6406new;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;

class PowerManagerImplTest {

	PowerManagerImpl pm = new PowerManagerImpl();
	SchedulingProcessImpl sp = new SchedulingProcessImpl();
	DataCenter dc = new DataCenter();
	
//	@Test
//	void ThermalRequest_Test() {
//	}
	
	@Test
	void PowerRequest_Test() {
		
		pm.managePowerCooling(true);
		assertTrue("Invalid Request from thermal mgmt", pm.coolingState == true || pm.coolingState == false );
		assertTrue("InValid Required Voltage",pm.L > 0 && pm.L < 2000);
		assertTrue("InValid Required Voltage-(Mutation)",pm.L >= 1 && pm.L <= 2000);
		
		Random rn = new Random();
		
		int ServerIDrange = 20 - 0 +1;
		int serverID = rn.nextInt(ServerIDrange) + 0;
		System.out.println("Server ID - "+serverID);
		
		int ServerVolRange = 200 - 50 +1;
		int serverVol = rn.nextInt(ServerVolRange) + 50;
		System.out.println("Server Vol - "+serverVol);
		
		pm.managePowerServer(serverVol,serverID);
		assertTrue("InValid ServerID",pm.serverID_PM > 0 && pm.serverID_PM < 20);
		assertTrue("InValid ServerID",pm.serverID_PM > 0 && pm.serverID_PM < 20);
		assertTrue("InValid ServerVoltage",pm.serverRoomVoltage > 0 && pm.serverRoomVoltage <= 200);
		assertTrue("InValid ServerVoltage-(Mutation)",pm.serverRoomVoltage >= 1 && pm.serverRoomVoltage <= 200);
		assertTrue("InValid Required Voltage",pm.L > 0 && pm.L < 2000);
		assertTrue("InValid Required Voltage-(Mutation)",pm.L >= 1 && pm.L <= 2000);
	}
	@Test
	void MPSVoltage_Test() {
		assertTrue("InValid Main Power Supply",pm.monitoredVoltage_MPS > 0 && pm.monitoredVoltage_MPS < 2000);
		assertTrue("InValid Main Power Supply-(Mutation)",pm.monitoredVoltage_MPS >= 1 && pm.monitoredVoltage_MPS <= 2000);
	}
	@Test
	void SPSVoltage_Test() {
		assertTrue("InValid Secondary Power Supply",pm.monitoredVoltage_SPS > 0 && pm.monitoredVoltage_SPS < 2000);
		assertTrue("InValid Secondary Power Supply-(Mutation)",pm.monitoredVoltage_SPS >= 1 && pm.monitoredVoltage_SPS <= 2000);
	}
	
}
