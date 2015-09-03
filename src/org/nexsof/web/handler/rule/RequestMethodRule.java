/*
 * RequestMethodRule.java (May 2013)
 *
 * Author: Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 */
package org.nexsof.web.handler.rule;

import org.nexsof.web.config.AllowedMethod;


/**
 * This object has the validation rule to check if the requested HTTP method is implemented 
 * in this web server or not
 *
 * @author Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 * @version 1.0 
 */
public class RequestMethodRule implements RequestRule {

	/** This is the HTTP request method from  <code>Simple</code> <code>Request</code> object to validate */
	private String method;
	
	/**
	 * Constructor for <code>RequestMethodRule</code> object. This allows to create HTTP request method
	 * validator object
	 *   
	 * @param method this is the method from request to validate
	 */

	public RequestMethodRule(String method){
		this.method = method;
	}
	
	/**
	 *  The requested method is validated against allowed method list from <code>AllowedMethod</code>
	 */
	@Override
	public boolean checkValid() {
		return AllowedMethod.getMethodSet().contains(method);
	}

}
