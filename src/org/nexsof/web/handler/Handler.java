/*
 * Handler.java (May 2013)
 *
 * Author: Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 */
package org.nexsof.web.handler;

import java.io.IOException;

import org.nexsof.web.core.HttpServerLoader;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.Status;


/**
 *  The <code>Handler</code> is used to provide the common handling methods, properties
 *  and template method to define the algorithm of serving response for a request. This is an 
 *  <code>abstract</code> class, so it must be implemented by the individual handlers (e.g. file handler, directory handler, etc.)
 *  
 * @author Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 * @version 1.0 
 */
public abstract class Handler {
	
	/** This is the HTTP request to handle */
	protected Request request;
	
	/** This is the HTTP response to serve based on the request*/
	protected Response response;
	
	/** This is the HTTP response status for the request*/
	protected Status status;
	
	/**
	 * Constructor for <code>Handler</code> object. This is used to create a handler to serve an HTTP request by populating an HTTP
	 * response object provided by Simple Framework. The response object is then send back to the requestor HTTP client by Simple framework 
	 * 
	 * @param request this is the request to handle
	 * @param response this is the response to populate by the implemented handler 
	 */
	public Handler(Request request, Response response){
		this(request, response, Status.NOT_IMPLEMENTED);
	}

	/**
	 * Constructor for <code>Handler</code> object. This is used to create a handler with HTTP status. This can be used
	 * when there is not too much handle other than responding status and some common headers. 
	 * 
	 * @param request this is the request to handle
	 * @param response this is the response to populate by the implemented handler 
	 * @param status this is status of the response
	 */
	public Handler(Request request, Response response, Status status){
		this.request = request;
		this.response = response;
		this.status = status;
	}

	/**
	 * This is used to populate the HTTP response object for an HTTP request
	 *  
	 * This is a template method pattern to give a common algorithm for all handler class. Otherwise different handler
	 * may send different and inconsistent response to client.
	 */
	public void serveResponse() throws IOException, Exception	{
		addCommonHeader();
		addHandlerResponse();
		
		// This is important to flush the response to the requestor client. Otherwise client will be waiting until timeout.
		response.close();
	}
	
	/**
	 *	This is to get the individual handler's custom response. The resource handler may return message body with file content
	 *  or Directory handler may populate message body with directory index 
	 */
	abstract protected void addHandlerResponse() throws IOException, Exception;
	
	
	/**
	 *  This is to populate common HTTP response header that are applicable to all 
	 *  handlers.
	 */
	private void addCommonHeader(){
	    long time = System.currentTimeMillis();
		
		response.setValue("Server", HttpServerLoader.getConfig().getServerVersion());
	    response.setDate("Date", time);
	}
}
