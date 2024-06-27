# Usar una imagen base de OpenJDK
FROM openjdk:11-jre-slim

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR generado en el contenedor
COPY target/clientes-apirest-0.0.1-SNAPSHOT.jar app.jar

# Copiar el archivo import.sql en el directorio de recursos
COPY src/main/resources/import.sql /app/import.sql

# Exponer el puerto en el que corre la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]