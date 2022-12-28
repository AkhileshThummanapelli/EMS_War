FROM mysql:latest
MAINTAINER Akhilesh

VOLUME ["/etc/mysql", "/var/lib/mysql"]
RUN /usr/bin/mysqld_safe & sleep 5s
RUN mysql -u root -p1234 -e "CREATE DATABASE ems"

EXPOSE 3306
