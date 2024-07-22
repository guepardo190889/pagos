# Usar una imagen base de OpenJDK
FROM openjdk:17-jdk-slim

# Añadir información de mantenimiento
LABEL maintainer="ing.seth.luis@gmail.com"

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR de la aplicación al directorio de trabajo
COPY target/pagos-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto en el que la aplicación se ejecutará
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]