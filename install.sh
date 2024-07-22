#!/bin/bash

#Este script automatiza la descarga y construcción del proyecto 'pagos'.

#Para que funcione correctamente debes tener instaladas y funcionando las siguientes herramientas
#-Git
#-Maven 3.6.3 o superior
#-Docker
#-Docker Compose

# Clonar el repositorio
git clone https://github.com/tu-usuario/pagos.git

# Cambiar al directorio del repositorio
cd pagos

# Ejectuar todas las tareas de construcción con Maven
mvn clean install test javadoc:javadoc package

# Construir la imagen Docker
docker build -t pagos-app:latest .

# Levantar los servicios con Docker Compose
docker-compose up -d

# Abrir Swagger del proyecto en el navegador
xdg-open http://localhost:8080/swagger-ui/index.html