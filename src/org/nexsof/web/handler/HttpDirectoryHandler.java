/*
 * HttpDirectoryHandler.java (May 2013)
 *
 * Author: Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 */
package org.nexsof.web.handler;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import org.nexsof.web.core.HttpServerLoader;
import org.nexsof.web.core.MimeType;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.Status;


/**
 * This is a directory listing handler an HTTP URI target is a directory and the directory
 * does not have any file from the index/welcome file configuration. This handler will only be utilised when web server has access to the directory. 
 * 
 * However this is a simple implementation of directory listing and it can be improved to use a template base directory listing to show more information with better
 * look and feel.
 * 
 * @author Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 * @version 1.0 
 */
public class HttpDirectoryHandler extends Handler {

	/** This is the directory to list */
	private File dir;
	 
	/**
	 * Constructor for <code>HttpDirectoryHandler</code> object. This is to list a directory index when 
	 * request target is a directory.
	 * 
	 * @param request this is the request to list a directory index
	 * @param response this is the response to populate with directory index
	 * @param dir this is the directory to list index
	 */
	public HttpDirectoryHandler(Request request, Response response, File dir){
		super(request, response);
		this.dir = dir;
	}
	/**
	 *  This is the directory handler to populate the HTTP response object with directory listing
	 */
	@Override
	protected void addHandlerResponse() throws IOException{
		 String dirTitle = "Index of " + request.getTarget(); 
 
		 final String dirHeader = "<html>" +
	 				"<head>" +
	 				"  <title>" + dirTitle + "</title>" +
	 				"</head>" +
	 				"<body>" +
	 				"<article><h1>" + dirTitle + "</h1>" +
	 					"<ol>";
	 					
	 
		 final String dirFooter = "</ol><br><h3>" + HttpServerLoader.getConfig().getServerVersion() + "</h3></br>" +
	 										"</body></html>";

		 
		 StringBuilder indexingHtml = new StringBuilder();
		 
		 // Header
		 indexingHtml.append(dirHeader);
		 
		 // Listing of a directory; can be improved for more information and better look and feel
		 String[] dirFiles = dir.list();
		 for (String fileName : dirFiles) {
			 String webPath = request.getTarget() + "/" + fileName;
			 indexingHtml.append("<li><a href='"+webPath+"'>"+ fileName+"</a></li>");
		 }
		 
		 // Footer
		 indexingHtml.append(dirFooter);
		 
		 int size = indexingHtml.length();
		 
		 PrintStream body = response.getPrintStream();
		 body.print(indexingHtml.toString());
		 
		 response.setContentLength(size);
		 response.setValue("Content-Type", MimeType.getHtmlType());
		 response.setStatus(Status.OK);

		 body.close();
		
	 }
}
