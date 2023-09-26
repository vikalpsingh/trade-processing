FROM anapsix/alpine-java
LABEL maintainer="vikalp.singh@gmail.com"
COPY /target/processing-1.0-SNAPSHOT.jar processing-1.0-SNAPSHOT.jar
CMD ["java","-jar","processing-1.0-SNAPSHOT.jar"]