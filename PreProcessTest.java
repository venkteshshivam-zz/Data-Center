package com.ucc.cs6406new;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.rules.Timeout;
import org.junit.*;
import org.junit.rules.Timeout.*;

class PreProcessTest {

	queryPreProcessImpl qp = new queryPreProcessImpl();
	DataCenter dc = new DataCenter();
	
	@Test
	public void testOptimize() {
		
		assertNotNull("Input query is not null",dc.Query);
		assertNotNull("Output from optimizer is not null", qp.optimize(dc.Query));
	}

	@Test
	public void testCheckCache() {
		
		assertNull("Found in cache hence null to scheduler",qp.checkCache(dc.Query));
		//assertFalse("Not in cache hence sending to scheduler", qp.checkCache(dc.Query).length>0);
		
		
	
	}
	


}
