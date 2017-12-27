@echo off
 setLocal EnableDelayedExpansion
 set CLASSPATH="
 for /R ../ %%a in (*.*) do (
   set CLASSPATH=!CLASSPATH!;%%a
 )
 set CLASSPATH=!CLASSPATH!"

java org.audit4j.microservice.Main