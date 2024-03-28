FROM openjdk:11-jdk

# Define el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el JAR de seguros al contenedor
COPY target/seguros-backend-0.0.1-SNAPSHOT.jar seguros.jar

# Expone el puerto 8080 (o el puerto que esté configurado en tu aplicación Spring Boot)
EXPOSE 8080

# Comando para ejecutar tu aplicación al iniciar el contenedor
CMD ["java", "-jar", "app.jar"]
