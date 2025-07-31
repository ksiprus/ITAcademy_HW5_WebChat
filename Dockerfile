FROM tomcat:10.1.43-jdk21-openjdk
LABEL authors="ksiprus"
COPY target/ITAcademy_HW5_WebChat.war /usr/local/tomcat/webapps/
WORKDIR /usr/local/tomcat/webapps
CMD ["catalina.sh", "run"]