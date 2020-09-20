# we are extending everything from tomcat:8.0 image ...
FROM tomcat:8.0
MAINTAINER abhishek.sen999@gmail.com
EXPOSE 8080
COPY target/AU_Management_System-0.0.1.war /usr/local/tomcat/webapps/