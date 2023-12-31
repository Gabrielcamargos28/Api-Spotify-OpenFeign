# Use a imagem openjdk como base
FROM openjdk:17

# Copie o JAR Spring Boot para o contêiner
COPY target/spotify-0.0.1-SNAPSHOT.jar spotify-0.0.1-SNAPSHOT.jar

#Defina o comando padrão a ser executado quando o contêiner for iniciado
CMD ["java", "-jar", "-Dserver.port=9000", "spotify-0.0.1-SNAPSHOT.jar"]