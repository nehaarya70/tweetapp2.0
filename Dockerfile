FROM openjdk:11
EXPOSE 8080
ADD target/tweetapp-service.jar tweetapp-service.jar
ENTRYPOINT ["java","-jar","/tweetapp-service.jar"]