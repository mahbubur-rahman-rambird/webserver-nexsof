/*
 * HttpOptionHandler.java (May 2013)
 *
 * Author: Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 */
package org.nexsof.web.handler;

import static org.simpleframework.http.Protocol.ALLOW;

import java.io.IOException;

import org.nexsof.web.config.AllowedMethod;
import org.nexsof.web.core.MimeType;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.Status;


/**
 * This is to handle the HTTP request method <code>OPTIONS</code>. It populates the <code>ALLOW</code> header
 *  with the allowed/implemented comma separated method list
 *
 * @author Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 * @version 1.0 
 */
public class HttpOptionHandler extends Handler {

	
	/**
	 * Constructor for <code>HttpOptionHandler</code> object. This allows to create an handler 
	 * for the OPTIONS method.
	 * 
	 * @param request this is the request for OPTIONS method
	 * @param response this is the response populate for OPTIONS request
	 */
	public HttpOptionHandler(Request request, Response response){
		super(request, response);
	}
	
	/**
	 *  This is used to put custom response header ALLOW for the OPTIONS handler
	 */
	@Override
	protected void addHandlerResponse() throws IOException, Exception{
		response.setValue(ALLOW, AllowedMethod.getMethodList());

		response.setContentType(MimeType.getHtmlType());
		response.setContentLength(0);
		response.setStatus(Status.OK);
	}

}
