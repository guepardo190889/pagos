package com.blackdeath.pagos.modelos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Modelo de error básico para enviar mensajes a los clientes
 * 
 * @author Seth Karim Luis Martínez
 * @since 2024-07-21
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorModel {

	/**
	 * Mensaje de este modelo de errores
	 */
	private String mensaje;
}
