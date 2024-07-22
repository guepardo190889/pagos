
# Proyecto de Pagos

Este proyecto es una aplicación Spring Boot para gestionar pagos, con integración de Kafka para la notificación de actualizaciones de estado de los pagos. Se realiza como parte del proceso de contratación en Banco BASE.

## Índice
1. [Descripción](#descripcion)
2. [Requisitos](#requisitos)
3. [Configuración](#configuración)
4. [Uso](#uso)
5. [API](#api)
6. [Integración con Kafka](#integración-con-kafka)

## Descripción
### Tecnologías y librerías
- Java 17: Un lenguaje de programación de alto nivel que ofrece características avanzadas de programación orientada a objetos
- Kafka: Un sistema de mensajería distribuido que permite la producción y consumo de eventos en tiempo real
- JUnit: Un framework de pruebas unitarias para Java que facilita la creación y ejecución de pruebas automatizadas, asegurando la calidad del código
- Spring Boot 3.3.2: Un framework que facilita la configuración rápida y eficiente de aplicaciones basadas en Spring, con una configuración mínima
- Lombok 1.18.24: Una biblioteca que reduce el código boilerplate mediante el uso de anotaciones para generar automáticamente métodos getter, setter, constructores y otros
- MapStruct 1.5.5.Final: Una biblioteca que facilita el mapeo de objetos entre diferentes tipos, especialmente útil para convertir DTOs a entidades y viceversa
- MapStruct Binding 0.2.0: Un complemento para integrar MapStruct con Lombok, permitiendo el uso de ambas bibliotecas sin conflictos.
- Jacoco 0.8.8: Una herramienta que genera reportes de cobertura de pruebas, ayudando a identificar qué partes del código han sido ejecutadas durante las pruebas
- SpringDoc OpenAPI 2.0.2 > Una biblioteca que genera y gestiona la documentación OpenAPI (Swagger), proporcionando una interfaz visual (Swagger UI) para explorar y probar las APIs

### Buenas prácticas
- Nombrado consistente > Uso de nombres descriptivos y coherentes para variables, clases y métodos. Sigue las convenciones de nombrado de Java: CamelCase para clases y métodos, lowerCamelCase para variables, y UPPER_CASE para constantes
- Documentación y comentarios > Utilización de JavaDoc para documentar campos, métodos y clases, proporcionando claridad y facilitando el mantenimiento del código
- Clean Code > Enfoque en escribir código legible, mantenible y claro, promoviendo la facilidad de mantenimiento y evolución del código
- DRY (Don't Repeat Yourself): Evitar la duplicación de código mediante la reutilización de componentes comunes
- KISS (Keep It Simple, Stupid): Mantener el código lo más simple y claro posible, evitando complejidades innecesarias
- Pruebas unitarias > Validar el comportamiento correcto del código mediante pruebas unitarias con frameworks como JUnit
- DTO (Data Transfer Object):  Uso de objetos de transferencia de datos entre capas. En este proyecto, los objetos 'Model' se utilizan para intercambiar información con los clientes
- Enumeradores: Se usan para controlar grupos de datos relacionados. Pueden incluir también una réplica de tablas pequeñas. Facilitan la lectura, comprensión y uso del código

### Arquitectura
El proyecto organiza con los siguientes componente:
- Controladores: Aquí se ubican los endpoints que se exponen para interactuar con los clientes. Sus archivos tienen la anotación @RestController y terminan con 'Controller'
- Mapeadores: Aquí se ubica la conversión de objetos de modelo a objetos de dominio y viceversa. Sus archivos terminan con 'Mapper'
- Servicios: Aquí se ubica la lógica de negocio. Sus archivos tienen la anotación @Service y terminan con 'Service'
- Transacciones: Aquí se ubican las transacciones. No debe haber lógica de negocio. Sus archivos tienen la anotación @Component y terminan con 'Transaccion'
- Repositorios: Aquí se ubica el acceso a los repositorios de datos. Sus archivos terminan con 'Repository'
- Entidades: Aquí se ubican las entidades de dominio que nos permiten el acceso a los datos. Sus archivos tienen la anotación @Entity. Se deben nombrar como las tablas en base de datos usando camel-case
- Modelos: Aquí se ubican los DTO que nos permiten intercambiar información con los clientes. Solo se deben usar en los controladores y servicios. Una entidad nunca debe usarse en un controlador. Deben contener solo los datos necesarios
- Productores: Aquí se ubican las clases que permiten producir tópicos de Kafka
- Consumidores: Aquí se ubican las clases que prmiten consumir tópicos de Kafka

## Requisitos

- Java 17
- Maven 3.6.3 o superior
- Docker y Docker Compose
- MySQL 8.0.29 o superior
- Kafka 3.2.3

## Configuración

### Script
Puedes descargar o copiar el script [install.sh](https://github.com/guepardo190889/pagos/blob/main/install.sh) y ejecutarlo para que haga todo por tí

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
   