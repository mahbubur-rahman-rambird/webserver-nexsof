@echo off
rem webserver-start.sh script to start web server
rem Author: Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)

IF "%1"=="" goto START_ERROR
IF "%2"=="" goto START_ERROR

goto END_ERROR

:START_ERROR
echo "Usage: start.bat [document-root-dir] [port-number]"
echo "Example command: start.bat c:\nexsof-www 80"
goto EXIT

:END_ERROR

if exist "%JAVA_HOME%" goto SET_SYSTEM_JAVA

echo JAVA_HOME is not set, unexpected results may occur.
echo Set JAVA_HOME to the directory of your local JDK to avoid this message.
echo --

goto SET_SYSTEM_JAVA

:SET_SYSTEM_JAVA
set JAVA=java

:END_SETTING_JAVA

:START

rem ********* run nexsof web server ***********

"%JAVA%" -jar nexsof-1.0.0.jar %*

:EXIT

pause