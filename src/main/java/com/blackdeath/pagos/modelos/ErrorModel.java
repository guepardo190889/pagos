package com.blackdeath.pagos.modelos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Modelo de error básico para enviar mensajes a los clientes
 * 
 * @author Seth Karim Luis Martínez
 * @since 2024-07-21
 */
@Schema(description = "Modelo que contiene un mensaje de error")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorModel {

	/**
	 * Mensaje de este modelo de error
	 */
	@Schema(description = "Mensaje de error")
	private String mensaje;
}
