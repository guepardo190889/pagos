INSERT IGNORE INTO estatus_pagos (clave, descripcion) VALUES ('GENERADO', 'Pago generado');
INSERT IGNORE INTO estatus_pagos (clave, descripcion) VALUES ('PAGADO', 'Pago realizado');
INSERT IGNORE INTO estatus_pagos (clave, descripcion) VALUES ('NO_PAGADO', 'Pago no efectuado');

INSERT IGNORE INTO usuarios (nombre, apellido_paterno, apellido_materno, rfc, fecha_creacion) 
VALUES ('Juan', 'Pérez', 'Rodríguez', 'PEREJ870101XXX', NOW());

INSERT IGNORE INTO cuentas (numero, clabe, saldo, id_usuario, fecha_creacion, fecha_activacion)
VALUES ('123456789012', '123456789012345678', 1000000.00, 1, NOW(), NOW());

INSERT IGNORE INTO destinatarios (nombre, apellido_paterno, apellido_materno, rfc, fecha_creacion) 
VALUES ('María', 'González', 'Martínez', 'GONZM870101XXX', NOW());