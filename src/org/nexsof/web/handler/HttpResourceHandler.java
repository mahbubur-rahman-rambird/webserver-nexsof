/*
 * HttpResourceHandler.java (May 2013)
 *
 * Author: Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 */
package org.nexsof.web.handler;

import static org.nexsof.web.util.StringUtil.isEmpty;
import static org.simpleframework.http.Protocol.IF_MODIFIED_SINCE;
import static org.simpleframework.http.Protocol.LAST_MODIFIED;
import static org.simpleframework.http.Protocol.PRAGMA;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.nexsof.web.core.ContentTypeByExtension;
import org.nexsof.web.core.MimeType;
import org.simpleframework.http.Method;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.Status;



/**
 * This is the most important handler for a file based web server that serves 
 * the HTTP request for a file/resource. It reads a file from the document root folder
 * and send the file content as a stream with appropriate content type so that client can handle
 * the file correctly.
 * 
 * In this implementation, it serves any file that are requested and available in the document root folder.
 * But for better security, a filter needs to be implemented to reject a file request if its type is
 * not allowed in the configuration.
 *
 * @author Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 * @version 1.0 
 */
public class HttpResourceHandler extends Handler {

	
	/** This is the file object to serve */
	private File file;
	 
	
	/**
	 *  Constructor for <code>HttpOptionHandler</code> object. This allows to create a resource handler to serve file
	 *  
	 * @param request this is the request for a resource to be served 
	 * @param response this is the response object to be populated with resource data
	 * @param file this is the file object to be served
	 */
	public HttpResourceHandler(Request request, Response response, File file){
		super(request, response);
		this.file = file;
	}
	
	/**
	 *  This is to populate the <code>response</code> object from the <code>FileInputStream</code> of file object
	 */
	@Override
	protected void addHandlerResponse() throws IOException{
		int indice, tempIndice;
	    byte tempArr[];
	    byte mainArr[] = new byte[0];
	    byte byteArr[] = new byte[65535];

	    try{
		    InputStream inputStream = new FileInputStream(file);
			// Prepare the output byte array
			for(indice = 0; (indice = inputStream.read(byteArr)) > 0;)  {
				tempIndice = mainArr.length + indice;
				tempArr = new byte[tempIndice];
				System.arraycopy(mainArr, 0, tempArr, 0, mainArr.length);
				System.arraycopy(byteArr, 0, tempArr, mainArr.length, indice);
				mainArr = tempArr;
		    }
	    }catch (IOException e) {
			// Forbidden error
	    	response.setStatus(Status.FORBIDDEN);
			response.setContentLength(0);
			response.setContentType(MimeType.getHtmlType());
			return;
		}
		
		// Set response header
		// Headers needs to set before the body writing start
		response.setStatus(Status.OK);
		response.setContentLength(mainArr.length);
		response.setDate(LAST_MODIFIED, file.lastModified());
		response.setContentType(ContentTypeByExtension.getType(file.getName()));

	    boolean isBodyRequired = true;
		
		if (!isModified()){
			response.setStatus(Status.NOT_MODIFIED);
			isBodyRequired = false;
		}else if (request.getMethod().equals(Method.HEAD)){
			isBodyRequired = false;
		}
		
		if (isBodyRequired){
			OutputStream bodyOutputStream = response.getOutputStream();
			bodyOutputStream.write(mainArr);
	
			bodyOutputStream.flush();
			bodyOutputStream.close();
		}
	}
	
	/**
	 * This is to check if the requested resource is modifed since the last access by requested client.
	 * 
	 * @return this returns if the requested resource is modifed from the last access
	 */
	private boolean isModified(){
		String pragma = request.getValue(PRAGMA);

		long checkDate =  request.getDate(IF_MODIFIED_SINCE);
		long lastModified = file.lastModified();

		if (checkDate > 0 && lastModified <= checkDate && !isBrowserRelaod(pragma)){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 *  This is to check if it is browser reload which ignores the if-modified-since conditions in the header
	 *  
	 * @param pragma this is the header indicating browser reload
	 * @return this returns whether it is a browser reload or not.
	 */
	private boolean isBrowserRelaod(String pragma){
		return !isEmpty(pragma) && pragma.equals("no-cache");
	}
	
}
