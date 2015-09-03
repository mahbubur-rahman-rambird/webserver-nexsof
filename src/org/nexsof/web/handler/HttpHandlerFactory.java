/*
 * HttpHandlerFactory.java (May 2013)
 *
 * Author: Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 */
package org.nexsof.web.handler;

import java.io.File;

import org.nexsof.web.config.AllowedMethod;
import org.nexsof.web.core.HttpServerLoader;
import org.nexsof.web.handler.rule.HttpRequestValidator;
import org.nexsof.web.handler.rule.RequestValidator;
import org.nexsof.web.util.IndexFileFilter;
import org.simpleframework.http.Method;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.Status;

import static org.simpleframework.http.Protocol.ALLOW;

/**
 * This is the HTTP handler factory which validate and examines the request object to 
 * create appropriate protocol handler.
 *
 * @author Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 * @version 1.0 
 */
public class HttpHandlerFactory implements HandlerFactory{

	/** This is the request to validate and pass it to the final handler */
	private Request request;
	
	/** This is the HTTP response object to pass to the final handler to populate*/
	private Response response;
	
	/**
	 * Constructor for <code>HttpHandlerFactory</code> object. This allows to create handler factory to 
	 * validate the request, find a handler and pass the response object to the found handler.
	 * 
	 * @param request the request to validate and examine
	 * @param response the response to pass to the handler to be populated 
	 */
	public HttpHandlerFactory(Request request, Response response){
		this.request = request;
		this.response = response;
	}
	/**
	 * This is an important method that validate the request to find a error handler or 
	 * Resource handler or directory handler if request looks OK to proceed.
	 *  
	 *  @return this returns a handler to process the request
	 */
	
	@Override
	public Handler getHandler(){
		
		RequestValidator requestValidator = new HttpRequestValidator(request);
		Status errorStatus = requestValidator.validate();
		if (errorStatus != null){
			
			// TODO: Needs to be handled better way
			if (errorStatus == Status.METHOD_NOT_ALLOWED){
				response.setValue(ALLOW, AllowedMethod.getMethodList());
			}
			return new HttpErrorHandler(request, response, errorStatus);
		}
		
		// OPTIONS Method handling
		if (request.getMethod().equals(Method.OPTIONS)){
			return new HttpOptionHandler(request, response);
		}
		
		// Security fix for path traversal (../) attack is part of Simple framework parsing
		//
		File file = new File(HttpServerLoader.getConfig().getDocumentRoot()+ File.separator + request.getPath());
		
		// Directory handling
		if (file.isDirectory()){
			File[] indexFiles = file.listFiles(new IndexFileFilter(HttpServerLoader.getConfig().getIndexExpr()));
			try{
				if (indexFiles.length == 0){
					return new HttpDirectoryHandler(request, response, file);
				}else{
					return new HttpResourceHandler(request, response, indexFiles[0]);
				}
			}catch (Exception e) {
				return new HttpErrorHandler(request, response, Status.FORBIDDEN);
			}
		}
		
		// Resource file access
		if (file.exists() && !file.isDirectory()){
			return new HttpResourceHandler(request, response, file);
		}else{
			return new HttpErrorHandler(request, response, Status.NOT_FOUND);
		}

	}
}
