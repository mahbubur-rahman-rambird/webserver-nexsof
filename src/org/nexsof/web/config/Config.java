/*
 * Config.java (May 2013)
 *
 * Author: Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 */
package org.nexsof.web.config;

/**
 * This is the configuration interface for the implemented web server. It provides 
 * the methods to get the important configuration control to initiate the web server with some parameter (e.g size of thread pool),
 * request validation controls (e.g. maximum URI length), etc.
 *  
 *
 * @author Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 * @version 1.0 
 */
public interface Config {

	/**
	 *  This is used to get the document root directory where resources/files are stored
	 *  to be server by the web server. Anything is put into this directory may be accessible over the web.
	 *  So enough cautions needs to be taken creating this directory and storing anything in that directory.
	 *  
	 *  This document root directory is expected to be a absolute path of an existing directory.
	 *  
	 *  However it has known security feature implemented and tested, but there could be some gaps as it has been developed in a short period of time.
	 *  So it is recommended not to run this web server in a direct Internet accessible server. 
	 *  
	 * @return this returns document root directory.  
	 */
	public String getDocumentRoot();
	
	/**
	 * This is to get the port number where web server will be listening to get HTTP request and serve.
	 * This returns an integer, however client of this service needs to have enough validation for a valid port number
	 * 
	 * @return this returns port number
	 */
	public int getPort();
	
	/**
	 * This is to get the size of Executor thread pool size which defines the maximum number of concurrent running
	 * threads to get request from an HTTP client or serving to an HTTP client.
	 *  
	 * @return this returns integer value for thread pool size
	 */
	public int getThreadPoolSize();
	
	/**
	 * This is to get the server version number for HTTP response "Server" header
	 * 
	 * @return this returns server's current signature
	 */
	public String getServerVersion();
	
	/**
	 * This is to get the simple regular expression for allowed index/welcome page in a directory.
	 * This is used when a directory URI is requested to serve the index files in that directory if any
	 * 
	 * @return this returns regular expression for index files in a directory
	 */
	public String getIndexExpr();
	
	/**
	 * This is used to get the maximum allowed URI length and this value is used in HTTP request validation
	 * Too long URI could be a security threat or cause performance issue in parsing and handling.
	 * Usually it is 1024 that most of the server can handle the request.
	 * 
	 * @return this returns a integer value for maximum URI length
	 */
	public int getMaxURILength();
	
	/**
	 * This is to get the request log enable flag. If flag is enabled, the web server will log all the
	 * request and response header information. Any logging usually leads to synchronised I/O operation.
	 * So enabling the request logging could cuase performance issue of this multi-threaded web server
	 *   
	 * @return this return (true/false) flag for logging enabled/disabled
	 */
	public boolean requestLogEnabled();
}
