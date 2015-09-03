/*
 * RequestValidator.java (May 2013)
 *
 * Author: Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 */
package org.nexsof.web.handler.rule;

import org.simpleframework.http.Status;

/**
 * This <code>RequestValidator</code> interface contain a method to validate the 
 * HTTP request object whether it is valid request or not.
 *
 * @author Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 * @version 1.0 
 */
public interface RequestValidator {
	
	/**
	 *  This is used to validate the HTTP request object before it passed to the actual 
	 *  resource handler
	 * @return this returns HTTP response status if the validation failed, otherwise null
	 */
	public Status validate();
}
