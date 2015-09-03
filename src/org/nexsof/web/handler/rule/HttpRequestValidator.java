/*
 * HttpRequestValidator.java (May 2013)
 *
 * Author: Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 */
package org.nexsof.web.handler.rule;

import org.simpleframework.http.Request;
import org.simpleframework.http.Status;

/**
 * This is an important object that is used to validate the Simple request object
 * if it is an valid request to served by the <code>HttpDirectoryHandler</code> or <code>HttpResourceHandler</code>
 * 
 * The Simple framework does the HTTP request parsing and put the different parts to the <code>Request</code> object. But it does
 * not validate the request. So it needs to be validated for HTTP protocol handling
 *
 * @author Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 * @version 1.0 
 */
public class HttpRequestValidator implements RequestValidator {
	
	/** This is the <code>Simple</code> request object to be validated */
	private Request request;
	
	/**
	 *  Constructor for <code>HttpRequestValidator</code> object. This allows to create a validator object for a request
	 *  that validates the request whether it is a valid HTTP request or not
	 *  
	 * @param request
	 */
	public HttpRequestValidator(Request request){
		this.request = request;
	}
	/**
	 *  This is used to validate the request against pre-defined validation rule.
	 *  The validation order is important for optimised performance
	 *  @return this returns a <code>Simple</code> <code>Status</code> object if the validation fails, otherwise null
	 */
	@Override
	public Status validate() {

		// Request URI too long; this is the first check to make sure that the client
		// is not trying to keep the server busy with unexpected long request URI
		RequestRule uriLengthRule = new URILengthRule(request.getTarget());
		if (!uriLengthRule.checkValid()){
			return Status.REQUEST_URI_TOO_LONG;
		}

		//Validate if request method is implemented or allowed
		RequestRule methodRule = new RequestMethodRule(request.getMethod());
		if (!methodRule.checkValid()){
			return Status.METHOD_NOT_ALLOWED;
		}
		
		// Validate if request line format matches the HTTP specification and has the correct protocol name
		RequestRule requestLineRule = new RequestLineFormatRule(request);
		if (!requestLineRule.checkValid()){
			return Status.BAD_REQUEST;
		}

		// Validate if protocol version is HTTP/1.0 or HTTP/1.1
		RequestRule verisonRule = new ProtocolVersionRule(request);
		if (!verisonRule.checkValid()){
			return Status.VERSION_NOT_SUPPORTED;
		}
		
		// Validation passed!
		return null;
	}

}
