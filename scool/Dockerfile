FROM openjdk:11
LABEL  mainteiner="jaiborge.com"
EXPOSE 9084
ADD target/scoolservms.jar scoolservms.jar
ENTRYPOINT ["java","-jar", "/scoolservms.jar"]