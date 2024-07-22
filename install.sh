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

# Limpiar y empaquetar el proyecto con Maven
mvn clean package

# Construir la imagen Docker
docker build -t pagos-app:latest .

# Levantar los servicios con Docker Compose
docker-compose up -d