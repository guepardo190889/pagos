INSERT IGNORE INTO estatus_pagos (clave, descripcion) VALUES ('GENERADO', 'Pago generado');
INSERT IGNORE INTO estatus_pagos (clave, descripcion) VALUES ('ENVIADO', 'Pago enviado');
INSERT IGNORE INTO estatus_pagos (clave, descripcion) VALUES ('NO_EFECTUADO', 'Pago no efectuado');

INSERT IGNORE INTO usuarios (nombre, apellido_paterno, apellido_materno, rfc, fecha_creacion) 
VALUES ('Juan', 'Pérez', 'Rodríguez', 'PEREJ870101XXX', NOW());

INSERT IGNORE INTO destinatarios (nombre, apellido_paterno, apellido_materno, rfc, fecha_creacion) 
VALUES ('María', 'González', 'Martínez', 'GONZM870101XXX', NOW());