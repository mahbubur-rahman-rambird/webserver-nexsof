/*
 * HttpContainer.java (May 2013)
 *
 *
 * Author: Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 */
package org.nexsof.web.core;

import java.util.Date;

import org.nexsof.web.handler.Handler;
import org.nexsof.web.handler.HandlerFactory;
import org.nexsof.web.handler.HttpHandlerFactory;

import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.Status;
import org.simpleframework.http.core.Container;


/**
 * This is the HTTP protocol implementation object that is passed to 
 * Simple framework server. The method <code>handle(request, response)</code> is called
 * when there is a complete request to serve.
 *
 * @author Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 * @version 1.0
 */
public class HttpContainer implements Container {

	/**
	 *  This is the main method that takes the parsed (by Simple framework) request to handled and response object to
	 *  be served.
	 *  @param request the client request object parsed by Simple framework
	 *  @param response this object to be served based on the client request
	 */
	public void handle(Request request, Response response) {
		
		try {
			HandlerFactory handlerFactory = new HttpHandlerFactory(request, response);
			Handler handler = handlerFactory.getHandler();
			handler.serveResponse();
		} catch(Exception e) {
			try{
				response.setStatus(Status.INTERNAL_SERVER_ERROR);
				response.close();
			}catch (Exception ex) {
				log("Critical error");
			}
		}
		
		// Logging may be disabled for better performance; I/O operations are synchronised and expensive
		if (HttpServerLoader.getConfig().requestLogEnabled()){
			log(request.getMethod() + " " +request.getTarget() + " " + response.getCode());
		}
	}
	
	/**
	 * This is a simplified logging to show request and response header information
	 * 
	 * @param msg this is message to log/display
	 */
	private void log(String msg){
		// Console output may cause performance use
		// It would be better to use standard logging library
		System.out.println(HttpServerLoader.getConfig().getServerVersion() + " "+ new Date() + ": " + msg);
	}
}