/*
 * RequestLineFormatRule.java (May 2013)
 *
 * Author: Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 */
package org.nexsof.web.handler.rule;

import org.simpleframework.http.Method;
import org.simpleframework.http.Request;

/**
 * This object has rule to validate the HTTP Request line
 * for format and individual components of the request line.
 * 
 * The request line must be in the following format
 * "<METHOD><Single space><Target URI><Single space><Protocol version>
 * e.g. GET /index.html HTTP/1.1
 * 
 *  
 *
 * @author Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 * @version 1.0 
 */
public class RequestLineFormatRule implements RequestRule {

	/** This is the must have protocol name in the request line*/
	private static final String HTTP="HTTP";
	
	/** This is the <code>Request</code> object to validate for the request line */
	private Request request;
	
	/**
	 * Constructor for <code>RequestLineFormatRule</code> object. This allows to create request line validator
	 * object  
	 * @param request this is the request to get request line to validate
	 */
	public RequestLineFormatRule(Request request){
		this.request = request;
	}
	
	/**
	 * Validate the request line with the HTTP specification
	 */
	@Override
	public boolean checkValid() {
		if ( request.getMethod().equals(Method.OPTIONS)){
			// For OPTIONS method, the target URI must be "*"
			if (!request.getTarget().equals("*")){
				return false;
			}
		}else{
			// for any method other than OPTIONS, the target URI must starts with "/"
			if (!request.getTarget().startsWith("/")){
				return false;
			}
		}
		
		if (!checkProtocol()){
			return false;
		}
		return true;
	}
	/**
	 * This is used to check the Protocol format as the last item in the request line
	 *  Support HTTP/1*DIGIT.1*DIGIIT
	 *  
	 *	@return this returns if request has the protocol information in the correct format   
	 */
	private boolean checkProtocol(){
		try{
			final char SP = ' ';
			final int VERSION_LEN = 8;
			String requestLine = request.getHeader().toString().split("\\r?\\n")[0];
			
			// First space after method name and before request URI
			int spPos = requestLine.indexOf(SP);
			
			// Second space before protocol
			spPos = requestLine.indexOf(SP, spPos + 1);
			
			String version = requestLine.substring(spPos+1);
			if (version.length() > VERSION_LEN){
				return false;
			}
			String protocol =  version.substring(0, 4);
			if ( protocol != null && protocol.equals(HTTP)){
				return true;
			}
		}catch (Exception e) {
			return false;
		}
		return false;
	}
}
