/*
 * IndexFileFilter.java (May 2013)
 *
 * Author: Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 * @version 1.0 
 */
package org.nexsof.web.util;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

/**
 * This is a <code>FileFilter</code> implementation to check whether a directory has any index file to server.
 * There are a standard libary from apache common for this. However it was implemented here to reduce the third party 
 * dependency in this project. 
 *
 * @author Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 * @version 1.0 
 */
public class IndexFileFilter implements FileFilter {
	
	/** This is the regular expression to match with filename if any matches*/
	private String regex; 
	
	/**
	 * Constructor for <code>IndexFileFilter</code> object. This allows to create
	 * new file filter for a regular expression to be applied to the filename in a directory
	 * 
	 * @param regex this is the regular expression to match with the file name
	 */
	public IndexFileFilter(String regex){
		this.regex = regex;
	}

	/**
	 * This is called <code>java.io.File.ListFiles</code> to find the matching files for a regular expression
	 * @param pathname this is the file path to match with a regular expression
	 */
	@Override
	public boolean accept(File pathname) {
		return Pattern.compile(regex).matcher(pathname.getName()).find();
	}
}
