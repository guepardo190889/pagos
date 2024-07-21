
# Proyecto de Pagos

Este proyecto es una aplicación Spring Boot para gestionar pagos, con integración de Kafka para la notificación de actualizaciones de estado de los pagos. Se realiza como parte del proceso de contratación en Banco BASE.

## Índice

1. [Requisitos](#requisitos)
2. [Instalación](#instalación)
3. [Configuración](#configuración)
4. [Uso](#uso)
5. [API](#api)
6. [Integración con Kafka](#integración-con-kafka)
7. [Documentación Adicional](#documentación-adicional)

## Requisitos

- Java 17
- Maven 3.6.3 o superior
- Docker y Docker Compose
- MySQL 8.0.29 o superior
- Kafka 3.2.3

## Instalación

### Clonar el Repositorio

```bash
git clone https://github.com/tu-usuario/pagos.git
cd pagos
```

### Construir el Proyecto

```bash
./mvnw clean install
```

## Configuración

### Configuración de Docker

Este proyecto utiliza Docker Compose para levantar los servicios de Kafka y Zookeeper. 

#### `docker-compose.yml`

```yaml
version: '3.9'
services:
  zookeeper:
    image: confluentinc/cp-zookeeper:7.2.1
    container_name: zookeeper
    ports:
      - "2181:2181"
    environment:
      ZOOKEEPER_CLIENT_PORT: 2181
      ZOOKEEPER_TICK_TIME: 2000

  kafka:
    image: confluentinc/cp-kafka:7.2.1
    container_name: kafka
    ports:
      - "9092:9092"
    environment:
      KAFKA_BROKER_ID: 1
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
    depends_on:
      - zookeeper
```

### Configuración de MySQL

Configura tu base de datos MySQL y crea una base de datos llamada `pagos`.

```sql
CREATE DATABASE pagos;
```

### Configuración de la Aplicación

Configura las propiedades de la aplicación en el archivo `src/main/resources/application.properties`.

```properties
spring.application.name=pagos

# Base de datos
spring.datasource.url=jdbc:mysql://localhost:3306/pagos
spring.datasource.username=tu-usuario
spring.datasource.password=tu-contraseña

spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.use_sql_comments=true

# Kafka
spring.kafka.bootstrap-servers=localhost:9092
spring.kafka.consumer.group-id=grupo-consumidor-pagos
spring.kafka.consumer.auto-offset-reset=earliest
spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.consumer.value-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.apache.kafka.common.serialization.StringSerializer

# Swagger
springdoc.api-docs.path=/v3/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
```

### Levantar los Servicios de Docker

```bash
docker-compose up -d
```

## Uso

### Levantar la Aplicación

```bash
./mvnw spring-boot:run
```

### Acceder a Swagger

Visita `http://localhost:8080/swagger-ui.html` para ver la documentación interactiva de la API.

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
   