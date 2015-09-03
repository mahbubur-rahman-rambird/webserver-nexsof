/*
 * HandlerFactory.java (May 2013)
 *
 * Author: Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 */
package org.nexsof.web.handler;

/**
 * This is an HTTP request handler factory that returns an appropriate handler 
 * that will server the request with a response.
 *
 * @author Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 * @version 1.0 
 */
public interface HandlerFactory {
	
	/**
	 * This is used to get an appropriate handler that can and will serve the HTTP request
	 * 
	 * @return this returns a handler to server an HTTP request
	 */
	public Handler getHandler();
}
