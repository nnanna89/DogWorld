package com.dwn.dogworld.dal;

import java.util.HashMap;
import java.util.Map;

/**
 * NamedQuery parameter helper to create the queries
 * 
 * @author Nnanna Madu
 * @since 4th Jan, 2015
 *
 */
public class QueryParameters {
	private Map<String, Object> parameters = null;
	
	private QueryParameters(String name, Object value){
		this.parameters = new HashMap<String, Object>();
		this.parameters.put(name, value);
	}
	
	public static QueryParameters with(String name, Object value){
		return new QueryParameters(name, value);
	}
	
	public QueryParameters and(String name, Object value){
		this.parameters.put(name, value);
		return this;
	}
	
	public Map<String, Object> parameters(){
		return this.parameters;
	}
}
