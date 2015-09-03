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

import org.nexsof.web.core.ContentTypeByExtension;
import org.nexsof.web.core.MimeType;

import junit.framework.TestCase;


/**
 * IndexTest.java
 *
 * <Purpose of the class>
 *
 * @author Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 * @version 1.0 
 */
public class MimeTypeTest extends TestCase{
	
	public void testMimeMatch(){
		assertEquals("image/png", MimeType.getType("png") );
		assertEquals("image/gif", MimeType.getType("gif"));
		assertEquals("application/x-javascript", MimeType.getType("js"));
		assertEquals(null, MimeType.getType("jssdfdsfds"));
		
	}
	public void testContentTypeByFileName(){
		assertEquals("image/png", ContentTypeByExtension.getType("test.png") );
		assertEquals("image/gif", ContentTypeByExtension.getType("test.gif"));
		assertEquals("application/x-javascript", ContentTypeByExtension.getType("test.js"));
		assertEquals("application/octet-stream", ContentTypeByExtension.getType("test.jssdfdsfds"));
		assertEquals("application/octet-stream", ContentTypeByExtension.getType("jssdfdsfds"));

	}

	
	
}
