FROM processing
LABEL maintainer="vikalp.singh@gmail.com"
COPY /target/processing-1.0-SNAPSHOT.jar /home/processing-1.0-SNAPSHOT.jar
CMD ["java","-jar","/home/processing-1.0-SNAPSHOT.jar"]