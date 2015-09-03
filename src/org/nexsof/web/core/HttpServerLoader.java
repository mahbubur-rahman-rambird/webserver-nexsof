/*
 * HttpServerLoader.java (May 2013)
 *
 * Author: Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 */
package org.nexsof.web.core;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Date;

import org.nexsof.web.config.Config;
import org.nexsof.web.config.HttpConfig;

import static org.nexsof.web.config.HttpConfig.DEFAULT_DOC_ROOT;
import static org.nexsof.web.config.HttpConfig.DEFAULT_PORT;

import org.simpleframework.http.core.Container;
import org.simpleframework.http.core.ContainerServer;
import org.simpleframework.transport.Server;
import org.simpleframework.transport.connect.Connection;
import org.simpleframework.transport.connect.SocketConnection;





/**
 *  Main class that runs as a stand alone java application and launches file based HTTP service.
 *
 * @author Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 * @version 1.0 
 */
public class HttpServerLoader{

	/** This is the in memory web server configuration */
	private static Config config;
	
	/** This is the flag which is set to true when the application has successfully started */
	private boolean isRunning;
	
	/** This is the Simple framework container interface to hold the HttpContainer instance  */
	private Container container;
	
	/** This is the Simple framework socket connection instance to manage the socket connection*/
	private Connection connection;
	
	/** This is the Simple framework socket connection address, mainly to store port number*/
	private SocketAddress address;
	
	/** This is the instance of a Simple framework container server that maintains the 
	 *  active connections and threads */
	private Server server;

	/**
	 *  Constructor for <code>HttpServerLoader</code> object. This allows the initiation of the components
	 *  that will be used to launch the web server.
	 *  
	 * @throws IOException
	 */
	public HttpServerLoader() throws IOException{
		container = new HttpContainer();
		server = new ContainerServer(container,config.getThreadPoolSize());
	    connection = new SocketConnection(server);
	    address = new InetSocketAddress(config.getPort());
	}
	
	/**
	 *  It returns the in memory HTTP server configuration (e.g size of thread pool). Many components will access this configuration
	 *  for request validation, so it has been made static method  
	 *  
	 * @return
	 */
	public static Config getConfig(){
		if (config == null){
			// If any chance this class method is called even though this is the web server loader class and it initialises this
			// configuration before it starts the HTTP serving threads.
			
			synchronized (HttpServerLoader.class) {
				if (config == null){
					config = new HttpConfig();
				}
			}
		}
		return config;
	}
	
	/**
	 *  This will instruct the server to start taking request and 
	 *  serving the client back
	 * 
	 */
	public void start() throws IOException {
		connection.connect(address);
		this.isRunning = true;
	}
	
	/**
	 * This would instruct the server to stop receiving request or serving the client
	 * In this implementation, this method is not called. But it can be utilised to stop the server gracefully 
	 */
	public void stop() throws IOException {
		if (isRunning){
			server.stop();
			connection.close();
			this.isRunning = false;
		}
	}

	/**
	 * This can be used to display the server's current status. However this has not been 
	 * used in this implementation, but the it is kept for future usage.
	 * 
	 * @return this returns whether the server is runing or not
	 */
	public boolean isRunning(){
		return isRunning;
	}
	
	/**
	 *  This is the main method that initiate the server components and
	 *  configuration from command line arguments. This expects 
	 *  document root directory for web server as the first parameter (args[0]) and
	 *  serving port number as the second parameter (args[1]).
	 *  
	 * @param args this takes the command line parameter for document root and port number. 
	 */
	public static void main(String[] args) {

		try{
			String webAccessHome = null;
			if (args.length <=0 || args[0].trim().length() <=0){
				webAccessHome = DEFAULT_DOC_ROOT;
				warn("Web server file access directory root directory was not provided.");
				warn("So server will be trying to load with the default directory:" + webAccessHome);
			}else{
				webAccessHome = args[0];
			}
			// Check if web home directory exists
			File homeDir = new File(webAccessHome);
			if (!homeDir.isDirectory()){
				throw new IllegalArgumentException("Web access home directory ("+webAccessHome+") does not exist. Please " +
						"create a directory or provide an existing correct and absolute path. \nHowever home directory can be passed to this application as args[0]");
			}
			
			int httpPort = 0; 
			if (args.length > 1){
				try{
					httpPort = Integer.valueOf(args[1]);
				}catch (Exception e) {
					throw new IllegalArgumentException("Invalid or no port number");
				}
			}else{
				httpPort = DEFAULT_PORT;
			}
			if (httpPort < 0 ){
				throw new IllegalArgumentException("Port number can not be a negative number");
			}
			if (httpPort <= 1024){
				warn("Port number 0 to 1024 might be reserved for existing services like FTP, HTTP");
			}

			// Start the server
			config = new HttpConfig(httpPort, webAccessHome);
			HttpServerLoader httpServer = new HttpServerLoader();
			httpServer.start();
			
			info("\n" + config.getServerVersion() + " web server has started...\n" +
					"---------------------------------------\n" +
					"Listening to port:"+ httpPort  + "\n" +
					"Document root:"+ config.getDocumentRoot() + "\n" +
					"You may access web server by browsing http://localhost:"+ httpPort);
			
		}catch (IOException ioe) {
			error("I/O error:" + ioe.getMessage());
			
		}catch (IllegalArgumentException iae) {
			error("Illegal argument:" + iae.getMessage());
		}
	}
	
	/**
	 *  This is to show information about the server's status/activities when it is running.
	 *  This is a simplified logging implementation. However it would be better to
	 *  utilise standard logging tool (e.g. log4j). In this task, the intention was to minimise the external
	 *  library utilisation.
	 * 
	 * @param message this is the message to output to console
	 */
	private static void info(String message){
		consoleOutput("Info", message);
	}
	
	/**
	 * This is to show warning information when web server can be started, but some parameters 
	 * may not be fully compatible
	 * 
	 * @param message this is the message to output to console
	 */
	private static void warn(String message){
		consoleOutput("Warn", message);
	}
	
	/**
	 * This is to show error when server can not be started with the provided arguments or configuration.
	 * 
	 * @param message this is the message to output to console
	 */
	private static void error(String message){
		consoleOutput("Error", message);
	}
	
	/**
	 * This is to format the log message with date and type and output to console or
	 * standard output.
	 * 
	 * @param type this is the type of logging (e.g. INFO, WARN)
	 * @param message the message to display
	 */
	private static void consoleOutput(String type, String message){
		System.out.println(new Date() + ": " + type + " " + message);
	}

}
