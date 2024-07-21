package com.blackdeath.pagos.utilerias.enumeradores;

import lombok.Getter;

/**
 * Enumerador de entidades
 * 
 * @author Seth Karim Luis Martínez
 * @since 2024-07-21
 */
@Getter
public enum Entidad {
	PAGO("Pago"), ESTATUS_PAGO("Estatus de Pago"), CUENTA("Cuenta"), USUARIO("Usuario"), DESTINATARIO("Destinatario");

	private String texto;

	/**
	 * Constructor privado que asigna el texto
	 * 
	 * @param texto
	 */
	private Entidad(String texto) {
		this.texto = texto;
	}
}
