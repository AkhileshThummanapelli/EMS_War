FROM akhileshdockerr/tomcat:latest
MAINTAINER Akhilesh
# COPY path-to-your-application-war path-to-webapps-in-docker-tomcat
WORKDIR .
COPY ./target/ems.war /opt/tomcat/webapps/
EXPOSE 8080
CMD ["/opt/tomcat/bin/catalina.sh", "run"]
