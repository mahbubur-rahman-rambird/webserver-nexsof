/*
 * URILengthRule.java (May 2013)
 *
 * Author: Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 * @version 1.0 
 */
package org.nexsof.web.handler.rule;

import org.nexsof.web.core.HttpServerLoader;

/**
 * This object has the rule to validate the request URI length
 *  if it is too long
 *
 * @author Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 * @version 1.0 
 */
public class URILengthRule implements RequestRule {

	/** This is the target URI from the HTTP request */
	private String uri;
	
	
	/**
	 *   Constructor for <code>URILengthRule</code> object. This allows to create a URI validator to validate 
	 *   the target URI from the client request.
	 *   
	 * @param uri this is the URI to validate for maximum length
	 */
	public URILengthRule(String uri){
		this.uri = uri;
	}
	
	/**
	 *  This is to check whether the requested target URI exceeds the pre-configured maximum length
	 */
	@Override
	public boolean checkValid() {
		return uri.length() <= HttpServerLoader.getConfig().getMaxURILength();
	}

}
