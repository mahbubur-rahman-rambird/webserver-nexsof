/*
 * HttpErrorHandler.java (May 2013)
 *
 * Author: Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 */
package org.nexsof.web.handler;

import java.io.IOException;

import org.nexsof.web.core.MimeType;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.Status;

/**
 * This is a generic HTTP error protocol handling object. Most of the error handling page
 * has almost same header but different error status. There is an exception for the "method not allowed"
 * error handling where "allowed method" needs to be in the header as well.
 *
 * @author Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 * @version 1.0 
 */
public class HttpErrorHandler extends Handler {

	/**
	 * Constructor for <code>HttpErrorHandler</code> object. This allows to create 
	 * an handler with known error status.
	 * 
	 * @param request this is the request to handle
	 * @param response this is the response to populate for the request
	 * @param status this is the error status for the response
	 */
	public HttpErrorHandler(Request request, Response response, Status status){
		super(request, response, status);
	}
	
	/**
	 *  This is the custom response data for all the error handlers.
	 */
	@Override
	protected void addHandlerResponse() throws IOException, Exception{
		// Error page content type is "text/html"
		response.setContentType(MimeType.getHtmlType());
		response.setContentLength(0);
		
		response.setStatus(this.status);
	}

}
