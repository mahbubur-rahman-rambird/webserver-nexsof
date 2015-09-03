/*
 * IndexTest.java
 *
 * <Purpose of the class>
 *
 * @author Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 * @version 1.0 
 */
package org.nexsof.web.util;

import java.io.File;
import java.io.FileFilter;

import junit.framework.TestCase;

import org.junit.Test;
import org.nexsof.web.util.IndexFileFilter;


/**
 * IndexTest.java
 *
 * <Purpose of the class>
 *
 * @author Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 * @version 1.0 
 */
public class IndexTest extends TestCase{
	
	private String indexRegex = "^(index|default)\\.html"; 
	public void testIndexMatch(){
		testIndexMatch("/www/index.html", indexRegex, Boolean.TRUE);
		testIndexMatch("/www/default.html", indexRegex , Boolean.TRUE);
	}

	public void testIndexNotMatch(){
		testIndexMatch("/www/defaul.html", indexRegex, Boolean.FALSE);
	}

	public void testIndexMatch(String filepath, String regex, Boolean match){
		File f = new File(filepath);

		FileFilter filter = new IndexFileFilter(regex);
		if (match){
			assertTrue(filter.accept(f));
		}else{
			assertFalse(filter.accept(f));
		}
	}
	
	
}
