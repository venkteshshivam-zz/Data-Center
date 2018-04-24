package com.ucc.cs6406new;


import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.ucc.cs6406.SensorImpl;
import com.ucc.cs6406.ThermalMgntImpl;

@RunWith(Parameterized.class)
public class ThermalECTest {
	public static final int OFF = 0;
	public static final int IDLE = 1;
	public static final int AC_Natural = 2;
	public static final int AC_Turbo = 3;
	public static final int AC_Water = 4;

	SensorImpl sensor_test = new SensorImpl();
	ThermalMgntImpl thermal_test = new ThermalMgntImpl();
	double[] serTemp = { 26.5 };
	double[] serTemp2 = { 25.0 };

	private double ac_temp, out_temp, server_temp;
	private int test_state, start_state;

	public ThermalECTest(double a, double o, double s, int t, int start) {
		// TODO Auto-generated constructor stub
		this.ac_temp = a;
		this.out_temp = o;
		this.server_temp = s;
		this.test_state = t;
		this.start_state = start;
	}

	@Parameters
	public static Collection data() {
		return Arrays.asList(new Object[][] { 
			{ 20, 10, 0, AC_Natural, IDLE }, 
			{ 20, 14, 0, AC_Turbo, IDLE },
//			{ 20, 10, 1, IDLE, AC_Natural }, 
//			{ 55, 16, 0, AC_Turbo, AC_Natural },
//			{ 55, 16, 1, IDLE, AC_Turbo },
//			{ 40, 10, 0, AC_Natural, AC_Turbo },
//			{ 45, 16, 0, AC_Turbo, AC_Water },
			
			});
	}

	@Test
	public void test() {
		// testing texts
		// ac_temp, out_temp, server_temp, test_state here, start_state in ThermalMgnt
		// double[] test1 = { 20, 10, 0, AC_Natural, IDLE };
		// double[] test2 = { 20, 14, 0, AC_Turbo, IDLE };

		// List list = new ArrayList<>();
		// list.add(test2);

		// initialize the flag define come to which state
		int flag = 1;

		// System.out.println(list.size());
		// for (int i = 0; i < list.size(); i++) {
		System.out.println("AC:" + this.ac_temp);
		System.out.println("OT:" + this.out_temp);
		sensor_test.setAcTemp_test(this.ac_temp);
		sensor_test.setOutTemp_test(this.out_temp);

		// set the avg_servertemp
		if (this.server_temp == 0) {
			// avg_temp higher than 26
			sensor_test.setSerTemp(serTemp);
		} else {
			// avg_temp lower than 26
			sensor_test.setSerTemp(serTemp2);
		}

		// set which state come to
		if (this.test_state == 1) {
			flag = 1;
			System.out.println("come into the test" + "IDLE");

		} else if (this.test_state == 2) {
			flag = 2; // come into the state AC_Natural
			System.out.println("come into the test" + "AC_Natural");

		} else if (this.test_state == 3) {
			flag = 3; // come into the state AC_Turbo
			System.out.println("come into the test" + "AC_Turbo");

		} else if (this.test_state == 4) {
			flag = 4; // come into the state AC_Water
			System.out.println("come into the test" + "AC_Water");

		}

		// start state
		if (this.start_state == 1) {
			// start from the idle
			int state = (int) this.start_state;
			thermal_test.setState(state);

		} else if (this.start_state == 2) {
			int state = (int) this.start_state;
			thermal_test.setState(state);
		} else if (this.start_state == 3) {
			int state = (int) this.start_state;
			thermal_test.setState(state);
		} else if (this.start_state == 4) {
			int state = (int) this.start_state;
			thermal_test.setState(state);
		} 
		
		

		System.out.println(sensor_test.getAcTemp_test() + " " + sensor_test.getOutTemp_test());
		thermal_test.update(sensor_test);
		System.out.println("update the sensor data");
		thermal_test.setTurn_on(true);
		thermal_test.autoThermalMgnt();

		switch (flag) {

		case 1:
			// IDLE

			System.out.println("1\n");

			break;

		case 2:
			// stay in the AC_Natural
			
			assertTrue(thermal_test.isAc_cooling());
			assertTrue(thermal_test.isNatural_cooling());
			assertFalse(thermal_test.isTurbo_cooling());
			assertFalse(thermal_test.isWater_cooling());
			System.out.println("2\n");

			break;

		case 3:
			// stay in the AC_Turbo

			assertTrue(thermal_test.isAc_cooling());
			assertTrue(thermal_test.isNatural_cooling());
			assertTrue(thermal_test.isTurbo_cooling());
			assertFalse(thermal_test.isWater_cooling());
			System.out.println("3\n");

			break;

		case 4:
			// stay in the AC_Water

			assertTrue(thermal_test.isAc_cooling());
			assertTrue(thermal_test.isNatural_cooling());
			assertFalse(thermal_test.isTurbo_cooling());
			assertTrue(thermal_test.isWater_cooling());
			System.out.println("4\n");

		default:
			break;
		}

	}

}

// }
