/*
 * ProtocolVersionRule.java (May 2013)
 *
 * Author: Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 */
package org.nexsof.web.handler.rule;

import org.simpleframework.http.Request;

/**
 * This object has rule to validate the request's HTTP version number.
 * It is important to tell the client whether a requested version is supported or not.
 *
 * @author Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 * @version 1.0 
 */
public class ProtocolVersionRule implements RequestRule {

	/**This is the request to validate for HTTP version number*/
	private Request request;
	
	/**
	 *  Constructor for <code>ProtocolVersionRule</code> object. This allows to create a protocol version validator
	 *  for a request.
	 * @param request this is the request to validate for supprted version
	 */
	public ProtocolVersionRule(Request request){
		this.request = request;
	}

	/**
	 * This is used to check whether requested HTTP version is supported.
	 * In this implementation, the supported protocols HTTP/1.0 and HTTP 1.1
	 * 
	 */
	@Override
	public boolean checkValid() {
		if (request.getMajor() != 1){
			return false;
		}else if (request.getMinor()  < 0  ||  request.getMinor() > 1){
			return false;
		}
		return true;
	}
}
