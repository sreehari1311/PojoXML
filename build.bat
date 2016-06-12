@echo off

title Building PojoXml

cls

@echo.
@echo Building PojoXml...
@echo.

set JAVA_HOME=C:/Program Files/Java/jdk1.6.0_14
Set JAVA_OPTIONS=%JAVA_OPTIONS% -Dpojoxml_testpath=F:\Study\WorkSpace\PojoXML\PojoXML\test\xml\
set ANT_HOME=E:/Software/EnterpriseTechnologies/framework/apache-ant-1.7.0-bin
set classpath=%classpath%;%ANT_HOME%/lib/ant.jar;%ANT_HOME%/lib/ant-launcher.jar;%ANT_HOME%/lib/ant-trax.jar;%ANT_HOME%/lib/ant-junit.jar;%JAVA_HOME%/lib/tools.jar

set path=%path%;%ANT_HOME%/bin
set path=%path%;%JAVA_HOME%;/bin

java   -Dpojoxml_testpath=F:\Study\WorkSpace\PojoXML\PojoXML\test\xml\ org.apache.tools.ant.Main %1

@echo.
