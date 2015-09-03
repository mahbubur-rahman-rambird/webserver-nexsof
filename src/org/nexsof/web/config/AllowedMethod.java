/*
 * AllowedMethod.java (May 2013)
 *
 * Author: Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 */
package org.nexsof.web.config;

import java.util.HashSet;
import java.util.Set;

/**
 * This is a enum type constants for the allowed HTTP request methods. For overall application performance
 * it is a enum type with some static objects which will be called for almost every request.
 * Please note, just adding new methods (e.g TRACE) here won't make them fully functional, 
 * rather could make problem down the line. There needs to be appropriate coding in the handler classes for the new methods.
 * 
 *  
 * @author Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 * @version 1.0 
 */
public enum AllowedMethod {
	METHOD_GET("GET"),
	METHOD_POST("POST"),
	METHOD_HEAD("HEAD"),
	METHOD_OPTIONS("OPTIONS");
	
	/** This is the method name for the enum constants AllowedMethod*/
	private final String method;
	
	/** This is a wrapper Set to maintain the allowed methods in a set which does not allow duplicattion
	 *  and checking whether a requested method is allowed is efficient using Set "contains" method
	 * */
	private static final Set<String> methodSet = new HashSet<String>();
	
	/** 
	 * This is wrapper String object which stores a comma separated method list that is used in the "ALLOWED"
	 * header of the response.
	 * */
	private static final String methodList; 

	/**
	 *  This is static block to prepare the wrapper Set and String object once and they 
	 *  are called almost every in request. So it is better to keep them in memory
	 */
	static {
		StringBuilder builder = new StringBuilder(1024);
		for (AllowedMethod allowedMethod:AllowedMethod.values()) {
			methodSet.add(allowedMethod.method);
			builder.append(allowedMethod.method + ",");
		}
		methodList = builder.subSequence(0, builder.lastIndexOf(",")).toString();
	}
	
	/**
	 * Constructor for <code>AllowedMethod</code> enum. This is will create AllowedMethod object for a method
	 * 
	 * @param method this is the method name to be allowed
	 */
	AllowedMethod(String method) {
		this.method = method;
	}
	
	/**
	 * This is to get the pre-build Method Set object which is utilised for request validation
	 * 
	 * @return this returns Set object of allowed/implemented request methods
	 */
	public static Set<String> getMethodSet(){
		return methodSet;
	}
	
	/**
	 * This is to get a comma separated allowed method list in a String object. It is used to return "ALLOWED" header of
	 * the response when a requested method is not allowed or "OPTIONS" request is received from the client.
	 * 
	 * @return this returns a string with comma separated allowed/implemented method name
	 */
	public static String getMethodList(){
		return methodList;
	}
}
