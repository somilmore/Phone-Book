FROM openjdk:17
EXPOSE 8080
ADD target/Phone-Book.war phone-book.war
ENTRYPOINT ["java", "-jar", "/phone-book.war"]
