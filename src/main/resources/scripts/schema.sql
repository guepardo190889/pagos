CREATE DATABASE IF NOT EXISTS pagos;
USE pagos;

CREATE TABLE IF NOT EXISTS `pagos` (
  `id` int PRIMARY KEY AUTO_INCREMENT COMMENT 'Identificador único del pago',
  `monto` decimal(11,2) COMMENT 'Monto del pago',
  `concepto` varchar(256) COMMENT 'Concepto del pago',
  `id_usuario` int COMMENT 'LLave foránea que hace referencia al Usuario que realiza el pago',
  `id_destinatario` int COMMENT 'Llave foránea que hace referencia al Destinatario del pago',
  `id_estatus` int COMMENT 'Llave foránea que hace referencia al Estatus del pago',
  `fecha_creacion` datetime COMMENT 'Fecha en se creó el pago',
  `fecha_aplicacion` datetime COMMENT 'Fecha en que se aplicó el pago'
);

CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` int PRIMARY KEY AUTO_INCREMENT COMMENT 'Identificador único del usuario',
  `nombre` varchar(128) COMMENT 'Nombre del usuario',
  `apellido_paterno` varchar(128) COMMENT 'Apellido paterno del usuario',
  `apellido_materno` varchar(128) COMMENT 'Apellido materno del usuario',
  `rfc` varchar(13) COMMENT 'Registro Federal de Contribuyentes del usuario',
  `fecha_creacion` datetime COMMENT 'Fecha en que se creó el registro del usuario'
);

CREATE TABLE IF NOT EXISTS `destinatarios` (
  `id` int PRIMARY KEY AUTO_INCREMENT COMMENT 'Identificador único del destinatario',
  `nombre` varchar(128) COMMENT 'Nombre del destinatario',
  `apellido_paterno` varchar(128) COMMENT 'Apellido paterno del destinatario',
  `apellido_materno` varchar(128) COMMENT 'Apellido materno del destinatario',
  `rfc` varchar(13) COMMENT 'Registro Federal de Contribuyentes del destinatario',
  `fecha_creacion` datetime COMMENT 'Fecha en que se creó el registro del destinatario'
);

CREATE TABLE IF NOT EXISTS `estatus_pagos` (
  `id` int PRIMARY KEY AUTO_INCREMENT COMMENT 'Identificador único del estatus del pago',
  `clave` varchar(16) COMMENT 'Clave del estatus del pago',
  `descripcion` varchar(128) COMMENT 'Descripción del estatus del pago'
);

ALTER TABLE `pagos` ADD FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`);

ALTER TABLE `pagos` ADD FOREIGN KEY (`id_destinatario`) REFERENCES `destinatarios` (`id`);

ALTER TABLE `pagos` ADD FOREIGN KEY (`id_estatus`) REFERENCES `estatus_pagos` (`id`);
