package com.ucc.cs6406new;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class queryPreProcessImpl implements queryPreProcess {
	
	
	@Override
	public String[] optimize (String query) {
		
		String[] opSt = {};
		if(query.length() ==0)
		{
			System.out.println("No query entered");
		}
		
		else {
			opSt = query.split(" ");
		}
		
	return opSt;
		
	}
	
	// Optimize the query for better and efficient use of resources.
	
	@Override
	public String[] checkCache(String query) {
		
		String[] flag = null;
		String opQuery[] = optimize(query);
		String checked[] = {"sample", "query"};
		ArrayList<String> ar = new ArrayList<String>();

		for(int i = 0; i < checked.length; i++)
		   {
		      if(Arrays.asList(opQuery).contains(checked[i]))
		      ar.add(checked[i]);
		      
		   }
		
		if(ar.size() == checked.length)
		{
			System.out.println("QueryPreProcess : These are the sample cached results");
			return flag;
		}
			
		else
			return opQuery;
	}
	
	
	
	
}
