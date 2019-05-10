FROM openjdk:8
ADD target/books-0.0.1-SNAPSHOT.jar books-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["nohup", "java","-jar", "books-0.0.1-SNAPSHOT.jar", "&"]