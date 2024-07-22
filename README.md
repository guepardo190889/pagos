
# Proyecto de Pagos

Este proyecto es una aplicación Spring Boot para gestionar pagos, con integración de Kafka para la notificación de actualizaciones de estado de los pagos. Se realiza como parte del proceso de contratación en Banco BASE.

## Índice

1. [Requisitos](#requisitos)
2. [Configuración](#configuración)
3. [Uso](#uso)
4. [API](#api)
5. [Integración con Kafka](#integración-con-kafka)

## Requisitos

- Java 17
- Maven 3.6.3 o superior
- Docker y Docker Compose
- MySQL 8.0.29 o superior
- Kafka 3.2.3

## Configuración

###Script
Puedes descargar o copiar el script [install.sh](https://github.com/guepardo190889/pagos/blob/main/pom.xml) y ejecutarlo para que haga todo por tí

### Clonar el Repositorio
```bash
git clone https://github.com/tu-usuario/pagos.git
cd pagos
```
### Configuración de Docker

Este proyecto utiliza Docker Compose para levantar los servicios de MySQL, Kafka y Zookeeper. 

### Construir la imagen de la aplicación
```bash
docker build -t pagos-app:latest .
```
### Levantar los Servicios de Docker (ejecutar dentro de la carpeta raíz del repositorio)

```bash
docker-compose up -d
```

## Uso

### Acceder a Swagger

Visita la [documentación interactiva](http://localhost:8080/swagger-ui.html) de la API

## API

### Endpoints Principales

#### Crear Pago

```http
POST /api/pagos
```

#### Actualizar Pago

```http
PATCH /api/pagos/{id}
```

#### Consultar Pago por ID

```http
GET /api/pagos/{id}
```

## Integración con Kafka

### Productor

El servicio `KafkaProducerService` envía mensajes al tópico `pagos-topic` cuando se actualiza un pago.

### Consumidor

El servicio `KafkaConsumerService` consume mensajes del tópico `pagos-topic`.

### Verificar Mensajes en Kafka

Para verificar los mensajes enviados a Kafka:

1. Accede al contenedor de Kafka:

   ```bash
   docker exec -it kafka /bin/bash
   ```

2. Usa la consola de consumidor de Kafka:

   ```bash
   kafka-console-consumer --bootstrap-server localhost:9092 --topic pagos-topic --from-beginning
   ```
   