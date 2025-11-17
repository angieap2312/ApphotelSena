# Usamos Java 21 como base
FROM eclipse-temurin:21-jdk-alpine

# Carpeta donde se copiará tu JAR dentro del contenedor
WORKDIR /app

# Copiamos el JAR generado por Maven
COPY target/AppHotelSena-0.0.1-SNAPSHOT.jar app.jar

# Puerto que expone la aplicación (igual que en application.properties)
EXPOSE 9000

# Comando para ejecutar la app
ENTRYPOINT ["java","-jar","app.jar"]