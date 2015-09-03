/*
 * ContentTypeByExtension.java (May 2013)
 *
 * Author: Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 */

package org.nexsof.web.core;

import javax.activation.MimetypesFileTypeMap;

import org.nexsof.web.util.StringUtil;

/**
 * This is a wrapper object for <code>MimeType<code> to find correct 
 * content type by file extension
 *
 * @author Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 * @version 1.0 
 */
public class ContentTypeByExtension {
	
	/**
	 * This is to get Content Type of a file to be served so that 
	 * HTTP client (e.g browser) can understand the file type and render properly
	 * 
	 * @param fileName this is the file name from where extension will be extracted 
	 * @return this returns Content Type based on a file name's extension
	 */
	public static String getType(String fileName){
		String extension = fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length());
		String contentType = null;
		
		if (!StringUtil.isEmpty(extension)){
			contentType = MimeType.getType(extension);
		}
		
		// Default content type when there is no or invalid file extension
		if (contentType == null){
	    	contentType = new MimetypesFileTypeMap().getContentType(fileName);
		}
	    
		return contentType;
	}

}
