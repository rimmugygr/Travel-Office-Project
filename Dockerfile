FROM openjdk:11
ADD target/travel360-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
CMD java -jar travel360-0.0.1-SNAPSHOT.jar
#docker build .
#docker run -p 8080:8080 'id'
