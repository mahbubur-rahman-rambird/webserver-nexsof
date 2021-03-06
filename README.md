Nexsof v1.0 web server
======================
Author: Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)

This is a file based multi-threaded web server developed on top of Simple Framework 5.1.4 (www.simpleframework.org)

Architecture
------------
* JDK 6 environment is required for this web server
* This web server implementation was coded from scratch on top of Simple framework.  
* HTTP request parsing, Thread pooling, Socket connections and keep-alive features are utilised from Simple Framework.
* However following key features were implemented part of this HTTP web server implementation technical test:

	** Serve file/resource from a document root folder (Status: 200, 304 and 403)
	** Serve directory index when request target is a directory (Status: 200, and 403)
	** GET, POST, HEAD and OPTIONS methods are implemented. 
	** Validation and handling bad HTTP request (Status:400)
	** HTTP error handling (Status: 405, 414, 500 and 505)
	** Web server launch error handling
	** Startup script and ant script (build.xml) to build the jar and distribution 
	** Separate eclipse testing project based on HTMLUnit/JUnit for integration and load testing ( Attached webserver-nexsof-test.zip )

Notes/Limitations:
-----------------
* As this project is dependent on Simple framework, it was difficult to write in-project unit testing because it was
  required to create all mock Simple framework object for unit testing. Alternatively it was easier to write an extensive end-to-end integration testing
  using HtmlUnit/JUnit that covers almost 80% of coding.
* As it is a file based web server, request query string and message body were ignored in request handling 


How to run?
----------
* A jar distribution (nexsof-1.0.0-dist.zip) contains webserver-start.sh (webserver-start.bat for windows) script which can be run 
  with a document root directory and port number.
  Usage command: "webserver-start.sh <document-root> <webserver-port-number>" 
  Example command: "webserver-start.sh /var/nexsof-www 8888"
  
* An Eclipse project export (webserver-nexsof.zip) has been attached which can be imported to eclipse and run from there. However it would
  require command line argument from eclipse for "document root" and "port number"
  
* Once web server is up and running, a separate testing project webserver-nexsof-test.zip can be loaded into eclipse and run the test suite 
  org.nexsof.web.suite.CompleteTestSuite. There is a README-TEST.txt for further details.


Important classes
-----------------
* HttpServerLoader.java: Launch the HTTP web server with HTTP request handler. It has the "main" method
* HttpConfig.java: Configuration parameter for the web server
* HttpContainer.java: Important class creates bridge between Simple framework and this web server implementation
* Handler.java: Template method patterns to give common algorithm for all handlers 
* HttpHandlerFactory.java: Creates appropriate handler for an HTTP request
* HttpResourceHandler.java: It is the most important handler that serves file/resource to the client
* HttpDirectoryhandler.java: Handle the directory listing when the request target is a a directory
* HttpRequestValidator.java: Validate HTTP request for bad request 

Further possible improvements :
--------------------------------
* At the moment, it serves any file that is request and available in the document root directory. But a security filter is needed
  to server certain types of file only.
* Code needs to be re-factored for "Method not allowed" error handling
* The code comments can be revised and improved.
* Configuration can be from an independent config file and better logging using standard tool
* Code may be further refactored and optimised

Declaration:
------------ 
All the code in the this project were written from the scratch (except Simple framework jar). However the following open source projects, 
examples and forum were reviewed to develop an optimised and robust web server:

* Apache web server
* Apache mina (asyncweb)
* Jetty
* Apache Tomcat servlet engine
* Web server example: https://github.com/jrudolph/Pooling-web-server/blob/master/src/main/java/virtualvoid/net/SimplePooledWebServer.java
* Web server example: https://github.com/mattmackenzie/httpd 
* Directory listing: http://perishablepress.com/better-default-directory-views-with-htaccess/
* Stackoverflow and other Internet developer resource
