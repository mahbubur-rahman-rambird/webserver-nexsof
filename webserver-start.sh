#!/bin/sh
#
# Nexsof/1.0 (Simple) web server start up script
# Author: Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
#
echo

if [ $# -lt 2 ] ; then
  echo "use: $0 <document-root> <http-port-number>"
  echo "Example command: $0 /nexsof-www 8888"
  exit 1
fi
WEBHOME=$1
PORT=$2

NOW=`date +%Y%m%d-%H%M`

# Check if web server home directory exists
if [ ! -d $WEBHOME ]
then
	echo "Web server document root $WEBHOME doesn't exist"
	exit 2
fi

# Check the port number
SAFERANGE=1024

if [ $PORT -le $SAFERANGE ]
then
	echo "Port number 0 to 1024 might already be reserved for the existing service like FTP, HTTP"
	echo "or restricted for the non-root users."
        echo "If that is the case, this web server may not be able to start with the given port number"
        echo "Choose a port number greater than 1024 to avoid this message or if the server did not start for the given port number"
fi

# Check if java home is set
if [ -z $JAVA_HOME ]
then
	echo "JAVA_HOME is not set, unexpected results may occur"
        echo "Set JAVA_HOME to the directory of your local JDK to avoid this message."
fi


# Set System JAVA
JAVA=java

# Start web server
echo "Starting web server..."

$JAVA -jar nexsof-1.0.0.jar ${WEBHOME} ${PORT}

