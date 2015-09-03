/*
 * HttpConfig.java (May 2013)
 *
 * @author Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 */
package org.nexsof.web.config;

/**
 *
 * This is the main configuration for this HTTP web server implementation. This provides the important configuration control or parameter
 * for the web server.  It was designed to be a thread-safe immutable object as it would be accessed in a multi-threaded environment .
 * 
 * In this implementation, the configuration are defined in java class variables and every changes here requires re-compilation.
 * It could be implemented with a configuration file to get these web server parameters.
 * 
 * However, in this task, the intention was to keep the web server functional but with minimal dependency
 *
 * @author Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 * @version 1.0 
 */
public class HttpConfig implements Config{
	
	/** This is the web server signature with server name, version number and mentioning key third party library reference*/
	private static final String VERSION = "Nexsof/1.0 (Simple 5.1.4)";

	/** This is the maximum number concurrent thread pool size for the each of request and response */
	private static final int THREAD_POOL_SIZE = 10;
	
	/** This is the maximum request URI length. it can be increased to higher value. but it is better to keep the value low
	 * for security and performance issue. Too long URI could cause performance issue in request parsing and memory usage.
	 * 
	 * However HTTP client usually uses POST method to pass the long data in the message body instead of URI
	 */
	private static final int MAX_URI_LENGTH = 1024;
	
	/** 
	 * This is the regular expression to find the matching index/welcome pages in a directory 
	 * when specific file/resource  is not reqested by the client
	 */
	private static final String INDEX_REGEX = "^(index|default)\\.html";
	
	
	/** This is the flag to enable/disable logging request and response headers */
	private static final boolean REQUEST_LOG_ENABLED = true;
	
	/** This is default port and it is utilised when port number is not given in command line arguments */
	public static final int DEFAULT_PORT = 80;
	
	/** 
	 * This is the default document root directory from where webserver will serve files 
	 * Web server tries to load with this document root if it is not provided as an argument 
	 * of the main class <code>HttpServerLoader</code> 
	 */
	public static final String DEFAULT_DOC_ROOT = "c:\\nexsof-www";

	/** 
	 * This is the document root of the running web server from where webserver will serve the file/resources
	 * This is initialised when the instance of this class created 
	 */
	private final String DOCUMENT_ROOT;
	
	/** This is the port number that server listens to. It is initialised when instance of this class is created.*/
	private final int PORT;
	
	/**
	 * Constructor for <code>HttpConfig</code> object. This allows to get an instance of this object 
	 * with default port and document root
	 */
	public HttpConfig(){
		this(DEFAULT_PORT, DEFAULT_DOC_ROOT);
	}
	
	/**
	 * Constructor for <code>HttpConfig</code> object. This allows to create an instance with a socket port number and
	 * document root directory. These two parameters can be command line parameter or default value from this class
	 * 
	 * @param port this is the socket port number for the web server to listen to
	 * @param documentRoot this is the document root directory for the web server to server from 
	 */
	public HttpConfig(int port, String documentRoot){
		this.PORT = port;
		this.DOCUMENT_ROOT = documentRoot;
	}
	
	/**
	 *  This is to get the document root directory of the web server. It is expected to be an previously created
	 *  directory and an absolute path
	 *  
	 *  @return this returns document root directory
	 */
	@Override
	public String getDocumentRoot(){
		return DOCUMENT_ROOT;
	}
	
	/**
	 * This is to get the socket port number that web server listen to
	 * 
	 * @return this returns the socket port number
	 */
	@Override
	public int getPort(){
		return this.PORT;
	}
	
	/**
	 * This is to get the concurrent thread pool size for the each of request and response processing
	 * 
	 * @return this returns the thread pool size for executor framework
	 */
	@Override
	public int getThreadPoolSize(){
		return THREAD_POOL_SIZE;
	}
	
	/**
	 *  This is to get the current server signature for HTTP response header
	 *  @return this returns the web server name and version number
	 */
	@Override
	public String getServerVersion(){
		return VERSION;
	}
	
	/**
	 * This is to get the standard regular expression for the web server index/welcome pages in the directories
	 * 
	 *  @return this returns regular expression of index/welcome pages
	 */
	@Override
	public String getIndexExpr(){
		return INDEX_REGEX;
	}
	
	/**
	 * This is to get the Maximum allowed URI length for request validation
	 *  (URI too long)
	 *  
	 *  @return this returns the maximum allowed URI length
	 */
	@Override
	public int getMaxURILength(){
		return MAX_URI_LENGTH;
	}
	
	/**
	 * This is to get the enable/disable flag for request/response header information logging
	 * 
	 * @return this returns the request logging enable/disable flag
	 */
	@Override
	public boolean requestLogEnabled(){
		return REQUEST_LOG_ENABLED;
	}
}
