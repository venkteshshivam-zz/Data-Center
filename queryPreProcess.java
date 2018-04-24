package com.ucc.cs6406new;

public interface queryPreProcess {

	
	public String[] optimize(String query);
	
	// Optimize the query for better and efficient use of resources.
	
	public String[] checkCache(String opQuery);
	
	// Check for the query results in the cache.
	
	
	
}
